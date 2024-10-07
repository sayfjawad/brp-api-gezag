package nl.rijksoverheid.mev.brpadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service voor BRP functionaliteit
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrpService {

    private final BrpClient client;
    private final TransactionHandler transactionHandler;

    private static final String BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN = "getBsnsMinderjarigeKinderen";

    /**
     * Ophalen persoonslijst
     *
     * @param bsn de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de persoonslijst
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Persoonslijst getPersoonslijst(final String bsn, final Transaction transaction) {
        Persoonslijst persoonslijst = client.opvragenPersoonslijst(bsn, transaction);

        return persoonslijst;
    }

    /**
     * Ophalen bsns van minderjarige kinderen
     *
     * @param bsn de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de BSNs van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public List<String> getBsnsMinderjarigeKinderen(final String bsn, final Transaction transaction) {
        Persoonslijst persoonslijstOuder = client.opvragenPersoonslijst(bsn, transaction);
        if(persoonslijstOuder != null) {
            transaction.setReceivedId(persoonslijstOuder.getReceivedId());
            transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN, persoonslijstOuder.getReceivedId(), transaction);

            return persoonslijstOuder.getBurgerservicenummersVanMinderjarigeKinderen();
        } else {
            return Collections.emptyList();
        }
    }
}
