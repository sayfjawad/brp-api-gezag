package nl.rijksoverheid.mev.brpadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.TransactionHandler;

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
    private static final String BRP_SERVICE_GET_PERSOONSGEGEVENS_KINDEREN = "getPersoonsgegevensKinderen";
    private static final String BRP_SERVICE_GET_PERSOONSGEGEVENS = "getPersoonsgegevens";

    /**
     * Ophalen persoonsgegevens
     *
     * @see getPersoonslijst()
     * @param bsn de bsn om op te halen
     * @param transaction de originele transactie
     * @return de persoonsgegevens or leeg
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Persoonslijst getPersoonsgegevens(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijst = getPersoonslijst(bsn, transaction);

        transaction.setReceivedId(persoonslijst.getReceivedId());
        transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_PERSOONSGEGEVENS, persoonslijst.getReceivedId(), transaction);

        return persoonslijst;
    }

    /**
     * Ophalen persoonslijst
     *
     * @param bsn de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de persoonslijst
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Persoonslijst getPersoonslijst(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijst = client.opvragenPersoonslijst(bsn, transaction);

        if (persoonslijst == null || !persoonslijst.hasAnyValue()) {
            throw new BrpException("Geen gezaghouders gevonden bij opgegeven BSN.");
        }

        return persoonslijst;
    }

    /**
     * Ophalen kinderen
     *
     * @param bsn de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de persoonsgegevens van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public List<Persoonslijst> getKinderen(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijstOuder = client.opvragenPersoonslijst(bsn, transaction);

        transaction.setReceivedId(persoonslijstOuder.getReceivedId());
        transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_PERSOONSGEGEVENS_KINDEREN, persoonslijstOuder.getReceivedId(), transaction);

        return ophalenKinderen(persoonslijstOuder, transaction);
    }

    /**
     * Ophalen bsns van minderjarige kinderen
     *
     * @param bsn de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de BSNs van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public List<String> getBsnsMinderjarigeKinderen(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijstOuder = client.opvragenPersoonslijst(bsn, transaction);

        transaction.setReceivedId(persoonslijstOuder.getReceivedId());
        transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN, persoonslijstOuder.getReceivedId(), transaction);

        return persoonslijstOuder.getBurgerservicenummersVanMinderjarigeKinderen();
    }

    private List<Persoonslijst> ophalenKinderen(final Persoonslijst persoonslijstOuder, final Transaction transaction) throws BrpException {
        List<Persoonslijst> resultaat = new ArrayList<>();
        List<String> bsnsKinderen = persoonslijstOuder.getBurgerservicenummersVanKinderen();

        for (String kind : bsnsKinderen) {
            resultaat.add(client.opvragenPersoonslijst(kind, transaction));
        }
        return resultaat;
    }
}
