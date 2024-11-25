package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule.GezagService;
import nl.rijksoverheid.mev.logging.LoggingContext;
import org.openapitools.model.AbstractGezagsrelatie;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Persoon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Service om te bepalen of personen <i>bevoegdheid tot gezag</i> hebben.
 */
@Service
public class BevoegdheidTotGezagService {

    private final BrpService brpService;
    private final GezagService gezagService;
    private final LoggingContext loggingContext;

    public BevoegdheidTotGezagService(
        final BrpService brpService,
        final GezagService gezagService,
        final LoggingContext loggingContext
    ) {
        this.brpService = brpService;
        this.gezagService = gezagService;
        this.loggingContext = loggingContext;
    }

    /**
     * Bepaal <i>bevoegdheid tot gezag</i> van persoon.
     * <p>
     * Voor een bsn van een minderjarige geeft deze methode alle gezagsrelaties
     * van deze persoon terug. Voor een bsn van een meerderjarige, geeft deze
     * service alle gezagsrelaties van de kinderen terug, dit kunnen ook
     * gezagsrelaties zijn met andere meerderjarigen dan degene waarvoor gezag
     * was opgevraagd. Voor een minderjarige ouder, geeft deze methode alle
     * gezaghebbenden van deze minderjarige terug, plus alle gezaghebbenden van
     * diens kinderen.
     *
     * @param gezagRequest <i>burgerservicenummer(s)</i> van de persoon/personen
     *                     van wie de <i>bevoegdheid tot gezag</i> bepaald moet worden
     * @return 0, 1 of meerdere gezagsrelaties
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer onverwacht
     *                                                       het gezag niet kan worden bepaald
     */
    public List<Persoon> bepaalBevoegdheidTotGezag(final GezagRequest gezagRequest) throws GezagException {
        List<String> burgerservicenummers = gezagRequest.getBurgerservicenummer();
        loggingContext.addBurgerservicenummers(burgerservicenummers);

        return burgerservicenummers.stream()
            .map(this::bepaalGezagVoorPersoon)
            .toList();
    }

    private Persoon bepaalGezagVoorPersoon(final String burgerservicenummerPersoon) {
        var gezagsrelaties = Stream
            .concat(
                gezagService.getGezag(Set.of(burgerservicenummerPersoon), burgerservicenummerPersoon).stream(),
                vindGezagsrelatiesVoorKinderen(burgerservicenummerPersoon)
            )
            .toList();

        return new Persoon()
            .burgerservicenummer(burgerservicenummerPersoon)
            .gezag(gezagsrelaties);
    }

    private Stream<AbstractGezagsrelatie> vindGezagsrelatiesVoorKinderen(final String burgerservicenummerPersoon) throws GezagException {
        Set<String> kinderen = brpService.getBsnsMinderjarigeKinderenOuderEnPartners(burgerservicenummerPersoon);

        return gezagService.getGezag(kinderen, burgerservicenummerPersoon).stream();
    }
}
