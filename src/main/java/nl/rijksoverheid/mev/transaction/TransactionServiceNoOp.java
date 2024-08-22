package nl.rijksoverheid.mev.transaction;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import nl.rijksoverheid.mev.gezagsmodule.service.PersoonlijstType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

/**
 * Placeholder service voor transacties
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "transaction-logging.enabled", havingValue = "false", matchIfMissing = true)
public class TransactionServiceNoOp implements TransactionHandler {

    @Override
    public Transaction createGmApiTransaction(final HttpServletRequest request, final HttpServletResponse response) {
        log.trace("createGmApiTransaction - {}, {}", request, response);

        return new Transaction();
    }

    @Override
    public void saveBrpServiceTransaction(
            final String path,
            final String receivedId,
            final Transaction originalTransaction) {
        log.trace("saveBrpServiceTransaction - {}, {}, {}", path, receivedId, originalTransaction);
    }

    @Override
    public void saveBrpAdapterTransaction(final String receivedId, final Transaction originalTransaction) {
        log.trace("saveBrpAdapterTransaction - {}, {}", receivedId, originalTransaction);
    }

    @Override
    public void saveGezagmoduleTransaction(
            final PersoonlijstType type,
            final String receivedId,
            final String route,
            final String soortGezag,
            final VeldenInOnderzoek veldenInOnderzoek,
            final Transaction originalTransaction) {
        log.trace("saveGezagmoduleTransaction - {}, {}, {}, {}, {}", type, receivedId, route, soortGezag, originalTransaction);
    }

    @Override
    public void updateTransaction(final HttpStatusCode statusCode, final Transaction transaction, final HttpServletRequest request) {
        log.trace("updateTransaction - {}, {}, {}", statusCode.value(), transaction, request);
    }

    /**
     * Sla asynchroon transaction op in de database
     *
     * @param transaction de transaction om op te slaan
     */
    public void saveTransaction(final Transaction transaction) {
        log.trace("saveTransaction - {}", transaction);
    }
}
