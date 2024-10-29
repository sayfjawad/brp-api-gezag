package nl.rijksoverheid.mev.brpadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service voor BRP functionaliteit
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrpService {

    private final BrpClient client;

    private static final String BRP_SERVICE_GET_BSNS_MINDERJARIGE_KINDEREN = "getBsnsMinderjarigeKinderen";

    /**
     * Ophalen persoonslijst
     *
     * @param bsn         de bsn om de persoonslijst voor op te halen
     * @return de persoonslijst
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Optional<Persoonslijst> getPersoonslijst(final String bsn) {
        return client.opvragenPersoonslijst(bsn);
    }

    /**
     * Ophalen bsns van minderjarige kinderen
     *
     * @param bsn         de bsn om de persoonslijst voor op te halen
     * @return de BSNs van de kinderen
     * @throws GezagException wanneer BRP communicatie misgaat
     */
    public Set<String> getBsnsMinderjarigeKinderenOuderEnPartners(final String bsn) {
        var optionalPersoonslijstOuder = client.opvragenPersoonslijst(bsn);
        List<Persoonslijst> partners = optionalPersoonslijstOuder.stream()
            .map(Persoonslijst::getHuwelijkOfPartnerschappen)
            .flatMap(List::stream)
            .map(HuwelijkOfPartnerschap::getBsnPartner)
            .filter(Objects::nonNull)
            .map(client::opvragenPersoonslijst)
            .flatMap(Optional::stream)
            .toList();

        Stream<String> burgerservicenummersVanKinderenVanOuder = optionalPersoonslijstOuder.stream()
            .flatMap(Persoonslijst::getBurgerservicenummersVanMinderjarigeKinderen);
        Stream<String> burgerservicenummersVanKinderenVanPartners = partners.stream()
            .flatMap(Persoonslijst::getBurgerservicenummersVanMinderjarigeKinderen);

        return Stream.concat(
                burgerservicenummersVanKinderenVanOuder,
                burgerservicenummersVanKinderenVanPartners
            )
            .collect(Collectors.toSet());
    }
}
