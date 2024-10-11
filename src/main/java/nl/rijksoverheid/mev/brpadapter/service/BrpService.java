package nl.rijksoverheid.mev.brpadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param bsn         de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de persoonslijst
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Optional<Persoonslijst> getPersoonslijst(final String bsn, final Transaction transaction) {
        return client.opvragenPersoonslijst(bsn, transaction);
    }

    /**
     * Ophalen bsns van minderjarige kinderen
     *
     * @param bsn         de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de BSNs van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public List<String> getBsnsMinderjarigeKinderen(final String bsn, final Transaction transaction) {
        List<String> bsns = new ArrayList<>();

        Optional<Persoonslijst> persoonslijstOuder = client.opvragenPersoonslijst(bsn, transaction);
        persoonslijstOuder.ifPresent(persoonslijst -> {
            transaction.setReceivedId(persoonslijst.getReceivedId());
            transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN, persoonslijst.getReceivedId(), transaction);

            bsns.addAll(persoonslijst.getBurgerservicenummersVanMinderjarigeKinderen());
        });

        return bsns;
    }
}
