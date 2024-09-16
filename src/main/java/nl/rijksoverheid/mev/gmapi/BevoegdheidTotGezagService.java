package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.openapitools.model.GezagOuder;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Persoon;
import org.openapitools.model.TweehoofdigOuderlijkGezag;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Service om te bepalen of personen <i>bevoegdheid tot gezag</i> hebben.
 */
@Service
public class BevoegdheidTotGezagService {

    private final BrpService brpService;
    private final GezagService gezagService;
    private final BSNValidator bsnValidator;

    public BevoegdheidTotGezagService(
        final BrpService brpService,
        final GezagService gezagService
    ) {
        this.brpService = brpService;
        this.gezagService = gezagService;
        this.bsnValidator = new BSNValidator();
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
     * @param transaction  de transactie zoals gemaakt bij het ontvangen van de
     *                     aanvraag
     * @return 0, 1 of meerdere gezagsrelaties
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer onverwacht
     *                                                       het gezag niet kan worden bepaald
     */
    public List<Persoon> bepaalBevoegdheidTotGezag(
        final GezagRequest gezagRequest,
        final Transaction transaction
    ) throws GezagException {
        List<String> burgerservicenummers = gezagRequest.getBurgerservicenummer();
        if (!bsnValidator.isValid(burgerservicenummers)) {
            return Collections.emptyList();
        }

        return burgerservicenummers.stream()
            .map(burgerservicenummer -> bepaalGezagVoorPersoon(burgerservicenummer, transaction))
            .toList();
    }

    private Persoon bepaalGezagVoorPersoon(String burgerservicenummer, Transaction transaction) {
        var afzonderlijkeGezagsrelaties = Stream
            .concat(
                gezagService.getGezag(List.of(burgerservicenummer), transaction).stream(),
                vindGezagsrelatiesVoorKinderen(burgerservicenummer, transaction)
            )
            .map(AbstractGezagsrelaties::from)
            .toList();

        // temporary solution to merge `TweehoofdigOuderlijkGezag` with the same minderjarige until
        // gezagService#getGezag returns typed responses
        var mergedTweehoofdigOuderlijkGezagsrelaties = afzonderlijkeGezagsrelaties.stream()
            .filter(TweehoofdigOuderlijkGezag.class::isInstance)
            .map(it -> (TweehoofdigOuderlijkGezag) it)
            .reduce(
                new HashMap<String, TweehoofdigOuderlijkGezag>(),
                (acc, e) -> {
                    var minderjarige = e.getMinderjarige().orElseThrow(); // not optional, should be fixed in OpenAPI Spec
                    var key = minderjarige.getBurgerservicenummer();

                    acc.merge(key, e, (e1, e2) -> new TweehoofdigOuderlijkGezag()
                        .type(e1.getType())
                        .minderjarige(e1.getMinderjarige().orElseThrow()) // not optional, should be fixed in OpenAPI Spec
                        .ouders(List.of(
                            new GezagOuder().burgerservicenummer(e1.getOuders().getFirst().getBurgerservicenummer()),
                            new GezagOuder().burgerservicenummer(e2.getOuders().getFirst().getBurgerservicenummer())
                        ))
                    );
                    return acc;
                },
                (acc1, acc2) -> {
                    acc2.forEach((key, value) ->
                        acc1.merge(key, value, (e1, e2) -> new TweehoofdigOuderlijkGezag()
                            .type(e1.getType())
                            .minderjarige(e1.getMinderjarige().orElseThrow()) // not optional, should be fixed in OpenAPI Spec
                            .ouders(List.of(
                                new GezagOuder().burgerservicenummer(e1.getOuders().getFirst().getBurgerservicenummer()),
                                new GezagOuder().burgerservicenummer(e2.getOuders().getFirst().getBurgerservicenummer())
                            ))
                        )
                    );
                    return acc1;
                }
            )
            .values()
            .stream();
        var gezagsrelatiesZonderTweehoofdigOuderlijkGezag = afzonderlijkeGezagsrelaties.stream()
            .filter(it -> !(it instanceof TweehoofdigOuderlijkGezag));
        var gezagsrelaties = Stream
            .concat(mergedTweehoofdigOuderlijkGezagsrelaties, gezagsrelatiesZonderTweehoofdigOuderlijkGezag)
            .toList();

        return new Persoon()
            .burgerservicenummer(burgerservicenummer)
            .gezag(gezagsrelaties);
    }

    private Stream<Gezagsrelatie> vindGezagsrelatiesVoorKinderen(final String bevraagdePersoon, final Transaction transaction) throws GezagException {
        List<String> kinderen = brpService.getBsnsMinderjarigeKinderen(bevraagdePersoon, transaction);
        List<Gezagsrelatie> gezagsrelaties = gezagService.getGezag(kinderen, transaction);
        return gezagsrelaties.stream()
            .filter(gezagsrelatie -> gezagsrelatie.isTweehoofdigOuderlijkGezag() || bevraagdePersoon.equals(gezagsrelatie.getBsnMeerderjarige()));
    }
}
