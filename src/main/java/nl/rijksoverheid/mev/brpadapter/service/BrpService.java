package nl.rijksoverheid.mev.brpadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

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
    public Persoonslijst getPersoonslijst(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijst = client.opvragenPersoonslijst(bsn, transaction);

        if (persoonslijst == null || !persoonslijst.hasAnyValue()) {
            throw new BrpException("Geen gezaghouders gevonden bij opgegeven BSN.");
        }

        return persoonslijst;
    }

    /**
     * Ophalen bsns van minderjarige kinderen
     *
     * @param bsn         de bsn om de persoonslijst voor op te halen
     * @param transaction de originele transactie
     * @return de BSNs van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public List<String> getBsnsMinderjarigeKinderenOuderEnPartners(final String bsn, final Transaction transaction) throws BrpException {
        Persoonslijst persoonslijstOuder = client.opvragenPersoonslijst(bsn, transaction);
        List<Persoonslijst> partners = persoonslijstOuder.getHuwelijkOfPartnerschappen().stream()
            .filter(hop -> hop.getBsnPartner() != null)
            .map(HuwelijkOfPartnerschap::getBsnPartner)
            .map(bsnPartner -> {
                try {
                    return client.opvragenPersoonslijst(bsnPartner, transaction);
                } catch (BrpException ex) {
                    log.info("Opgevraagde persoon bestaat niet");
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .toList();

        transaction.setReceivedId(persoonslijstOuder.getReceivedId());
        transactionHandler.saveBrpServiceTransaction(BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN, persoonslijstOuder.getReceivedId(), transaction);

        List<String> kinderen = persoonslijstOuder.getBurgerservicenummersVanMinderjarigeKinderen();
        kinderen.addAll(partners.stream().map(Persoonslijst::getBurgerservicenummersVanMinderjarigeKinderen)
            .mapMulti((final List<String> list, final Consumer<String> consumer) -> {
                list.forEach(consumer::accept);
            }).toList());

        return kinderen.stream()
            .distinct().toList();
    }
}
