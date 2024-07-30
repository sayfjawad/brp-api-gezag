package nl.rijksoverheid.mev.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.correlation.CorrelationIdService;
import nl.rijksoverheid.mev.gezagsmodule.service.PersoonlijstType;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;

/**
 * Service for handling transactions
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "transaction-logging.enabled", havingValue = "true")
public class TransactionService implements TransactionHandler {

    @Value("${transaction.backup.location}")
    private String transactionBackupLocation;

    private final Executor executor;
    private final TransactionRepository repository;
    private final CorrelationIdService correlationIdService;

    private static final String KEY_CORRELATION_ID = "correlation-id";
    private static final String KEY_START_ATTRIBUTE = "startTime";
    private static final String KEY_OIN = "oin";
    private static final String KEY_TRANSACTION_ID = "TransactionID";
    private static final String APPLICATIE_NAAM_GM_API = "gm-api";
    private static final String APPLICATIE_NAAM_BRP_ADAPTER = "brp-adapter";
    private static final String METHODE_OPVRAGEN_PERSOONSLIJST = "opvragenPersoonslijst";
    private static final String APPLICATIE_NAAM_GEZAGSMODULE = "gezagsmodule";
    private static final String GEZAGSMODULE_VERSIE = "2.2.1";
    private static final String GEZAGSMODULE_GET_GEZAG = "getGezag";
    private static final String GEZAGSMODULE_VRAGENMODEL_PLOUDER1 = "gezagsmodule/vragenmodel/plOuder1";
    private static final String GEZAGSMODULE_VRAGENMODEL_PLOUDER2 = "gezagsmodule/vragenmodel/plOuder2";
    private static final String GEZAGSMODULE_VRAGENMODEL_PLNIETOUDER = "gezagsmodule/vragenmodel/plNietOuder";
    private static final String GEZAGSMODULE_VRAGENMODEL_PLPERSOON = "gezagsmodule/vragenmodel/plPersoon";
    private static final String APPLICATIE_NAAM_BRP_SERVICE = "brp-service";

    public TransactionService(final TransactionRepository repository,
            final CorrelationIdService correlationIdService) {
        this.repository = repository;
        this.correlationIdService = correlationIdService;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public Transaction createGmApiTransaction(final HttpServletRequest request, final HttpServletResponse response) {
        String dienstverlener = request.getHeader(KEY_OIN);
        correlationIdService.prepareForProcess(request.getHeader(KEY_CORRELATION_ID), dienstverlener);
        String correlationId = correlationIdService.getCorrelationIdForProcess();

        MDC.put(KEY_CORRELATION_ID, correlationId);

        Transaction transaction = new Transaction(
                correlationId,
                dienstverlener,
                null,
                request.getRequestURI(),
                APPLICATIE_NAAM_GM_API,
                Timestamp.from(Instant.now()));
        response.setHeader(KEY_CORRELATION_ID, correlationId);
        response.setHeader(KEY_TRANSACTION_ID, correlationId);
        request.setAttribute(KEY_START_ATTRIBUTE, System.currentTimeMillis());

        return transaction;
    }

    @Override
    public void saveBrpServiceTransaction(
            final String path,
            final String receivedId,
            final Transaction originalTransaction) {
        Transaction transaction = new Transaction(
                originalTransaction.getGeneratedId(),
                receivedId,
                originalTransaction.getDienstverlenerId(),
                Timestamp.from(Instant.now()));
        transaction.setApplicationName(APPLICATIE_NAAM_BRP_SERVICE);
        transaction.setGezagmodulePath(path);
        transaction.setDienst(originalTransaction.getDienst());

        saveTransaction(transaction);
    }

    @Override
    public void saveBrpAdapterTransaction(final String receivedId, final Transaction originalTransaction) {
        Transaction transaction = new Transaction(
                originalTransaction.getGeneratedId(),
                receivedId,
                originalTransaction.getDienstverlenerId(),
                Timestamp.from(Instant.now()));
        transaction.setApplicationName(APPLICATIE_NAAM_BRP_ADAPTER);
        transaction.setGezagmodulePath(METHODE_OPVRAGEN_PERSOONSLIJST);
        transaction.setDienst(originalTransaction.getDienst());

        saveTransaction(transaction);
    }

    @Override
    public void saveGezagmoduleTransaction(
            final PersoonlijstType type,
            final String receivedId,
            final String route,
            final String soortGezag,
            final VeldenInOnderzoek veldenInOnderzoek,
            final Transaction originalTransaction) {
        Transaction transaction = new Transaction(
                originalTransaction.getGeneratedId(),
                receivedId,
                originalTransaction.getDienstverlenerId(),
                Timestamp.from(Instant.now()));
        transaction.setApplicationName(APPLICATIE_NAAM_GEZAGSMODULE);
        String path = getPath(type);
        transaction.setGezagmodulePath(path);
        if (GEZAGSMODULE_GET_GEZAG.equals(path)) {
            transaction.setGezagmoduleVersie(GEZAGSMODULE_VERSIE);
        }
        transaction.setGezagmoduleRoute(route);
        transaction.setSoortGezag(soortGezag);
        transaction.setDienst(originalTransaction.getDienst());
        if (veldenInOnderzoek != null && veldenInOnderzoek.hasValues()) {
            String asString = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                asString = mapper.writeValueAsString(veldenInOnderzoek);
            } catch (JsonProcessingException ex) {
                log.error("Error vertalen naar string van velden in onderzoek object", ex);
            }
            transaction.setGezagmoduleGezagSoort(asString);
        }

        saveTransaction(transaction);
    }

    @Override
    public void updateTransaction(final HttpStatusCode statusCode, final Transaction transaction, final HttpServletRequest request) {
        transaction.setResponseCode(String.valueOf(statusCode.value()));

        long start = (Long) request.getAttribute(KEY_START_ATTRIBUTE);
        long current = System.currentTimeMillis();
        long executeDuration = current - start;

        log.info("Aanvraag afgehandeld voor path: {}, took: {}", request.getRequestURI(), executeDuration);

        saveTransaction(transaction);
    }

    /**
     * Sla asynchroon transaction op in de database
     *
     * @param transaction de transaction om op te slaan
     */
    public void saveTransaction(final Transaction transaction) {
        this.executor.execute(new SaveTransactionTask(repository, transaction, transactionBackupLocation));
    }

    private String getPath(final PersoonlijstType type) {
        if (type == null) {
            return GEZAGSMODULE_GET_GEZAG;
        } else {
            return switch (type) {
                case PERSOON ->
                    GEZAGSMODULE_VRAGENMODEL_PLPERSOON;
                case OUDER1 ->
                    GEZAGSMODULE_VRAGENMODEL_PLOUDER1;
                case OUDER2 ->
                    GEZAGSMODULE_VRAGENMODEL_PLOUDER2;
                case NIET_OUDER ->
                    GEZAGSMODULE_VRAGENMODEL_PLNIETOUDER;
                default ->
                    GEZAGSMODULE_GET_GEZAG;
            };
        }
    }
}
