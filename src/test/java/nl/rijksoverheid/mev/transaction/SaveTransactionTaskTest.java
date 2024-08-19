package nl.rijksoverheid.mev.transaction;
import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.dao.QueryTimeoutException;

/**
 * Tests for saving transaction task
 */
@ExtendWith(MockitoExtension.class)
class SaveTransactionTaskTest {

    @Mock
    private TransactionRepository repository;

    private static final String GENERATED_ID = "mocked";
    private static final String TEMP_DIR = "testing";

    @Test
    void saveTransactionTaskWithoutTransactionAndTransactionLocationDoesNotThrowException() {
        SaveTransactionTask task = new SaveTransactionTask(repository, null, null);

        assertDoesNotThrow(task::run);
    }

    @Test
    void saveTransactionTaskWithTransactionAndWithoutTransactionLocationDoesNotThrowException() {
        Transaction transaction = getTransaction(1);
        SaveTransactionTask task = new SaveTransactionTask(repository, transaction, null);

        assertDoesNotThrow(task::run);
    }

    @Test
    void saveTransactionTaskWithoutTransactionAndWithTransactionLocationDoesNotThrowException() {
        SaveTransactionTask task = new SaveTransactionTask(repository, null, TEMP_DIR);

        assertDoesNotThrow(task::run);
    }

    @Test
    void saveTransactionTaskExpectingTransactionToBeSaved() {
        Transaction transaction = getTransaction(1);
        SaveTransactionTask task = new SaveTransactionTask(repository, transaction, TEMP_DIR);
        task.run();

        verify(repository, times(1)).save(transaction);
        assertTrue(verifyBackups(0));
    }

    @Test
    void saveTransactionTaskExpectingTransactionSaveToFailAndBackupToBeCreated() {
        Transaction transaction = getTransaction(1);
        when(repository.save(transaction)).thenThrow(QueryTimeoutException.class);

        SaveTransactionTask task = new SaveTransactionTask(repository, transaction, TEMP_DIR);
        task.run();

        verify(repository, times(1)).save(transaction);
        assertTrue(verifyBackups(1));
    }

    @Test
    void saveTransactionTaskExpectingTransactionSaveToFailAndBackupToBeCreatedAndThenRestored() {
        Transaction transaction = getTransaction(1);
        when(repository.save(transaction))
                .thenThrow(QueryTimeoutException.class)
                .thenReturn(transaction);

        SaveTransactionTask task = new SaveTransactionTask(repository, transaction, TEMP_DIR);
        task.run();
        SaveTransactionTask secondTask = new SaveTransactionTask(repository, null, TEMP_DIR);
        secondTask.run();

        verify(repository, times(2)).save(any(Transaction.class));
        assertTrue(verifyBackups(0));
    }

    @AfterEach
    public void cleanUp() {
        File tempDir = new File(TEMP_DIR);

        if (!tempDir.delete()) {
            System.out.println("Failed to clean up after running unit test for save transaction task");//NOSONAR
        }
    }

    private Transaction getTransaction(final int no) {
        return new Transaction(GENERATED_ID + no, null, null, Timestamp.from(Instant.now()));
    }

    private boolean verifyBackups(final int amountExpected) {
        File tempDir = new File(TEMP_DIR);
        File[] files = tempDir.listFiles();

        if (amountExpected == 0) {
            return files == null || files.length == amountExpected;
        } else {
            return files != null && files.length == amountExpected;
        }
    }
}
