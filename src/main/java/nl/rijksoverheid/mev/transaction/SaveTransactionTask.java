package nl.rijksoverheid.mev.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.io.*;
import java.nio.file.Files;

/**
 * Asynchroon opslaan van een transactie in de transactie database
 */
public class SaveTransactionTask implements Runnable {

    private final TransactionRepository repository;
    private final Transaction transaction;
    private final String transactionBackupLocation;

    private static final String EXTENSION = ".ser";
    private static final Logger log = LoggerFactory.getLogger(SaveTransactionTask.class);

    public SaveTransactionTask(final TransactionRepository repository,
            final Transaction transaction, final String transactionBackupLocation) {
        this.repository = repository;
        this.transaction = transaction;
        this.transactionBackupLocation = transactionBackupLocation;
    }

    /**
     * Slaat transactie op alsook eventuele gebackupped transacties die eerder
     * niet konden worden opgeslagen
     */
    @Override
    public void run() {
        if (transactionBackupLocation != null && !transactionBackupLocation.isEmpty()) {
            restorePotentialBackups();
        } else {
            log.warn("Cannot restore backups of transactions since no transactionBackupLocation was provided");
        }

        if (transaction != null) {
            storeTransaction(transaction, null);
        } else {
            log.warn("SaveTransactionTask was run without a transaction");
        }
    }

    private void restorePotentialBackups() {
        File dir = new File(transactionBackupLocation);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                Transaction toRetry = null;
                try (final FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                    toRetry = (Transaction) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    log.error("Error while reading transactions from backup location", ex);
                }

                if (toRetry != null) {
                    storeTransaction(toRetry, file);
                }
            }
        }
    }

    private void storeTransaction(final Transaction toStore, final File backup) {
        try {
            if (toStore != null) {
                repository.save(toStore);

                if (backup != null && backup.exists()){
                    Files.delete(backup.toPath());
                }
            }
        }
        catch (IOException ex) {
            log.error("Error while deleting backup with name: {} from backup location", backup.getName());
            storeBackup(toStore);
        }
        catch (DataAccessException ex) {
            log.error("Error while saving transaction", ex);
            storeBackup(toStore);
        }
    }

    private void storeBackup(final Transaction toStore) {
        if (transactionBackupLocation != null && !transactionBackupLocation.isEmpty()) {
            try (final FileOutputStream fos = new FileOutputStream(getFile(toStore)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(toStore);
            } catch (IOException ex) {
                log.error("Error while storing transaction on backup location", ex);
            }
        } else {
            log.warn("Cannot write backup of transaction since no transactionBackupLocation was provided");
        }
    }

    private File getFile(final Transaction toStore) {
        File file = new File(transactionBackupLocation);
        file.mkdirs();

        String folder = transactionBackupLocation + "/";
        return new File(folder, toStore.getGeneratedId() + toStore.getTransactionDatetime() + EXTENSION);
    }
}
