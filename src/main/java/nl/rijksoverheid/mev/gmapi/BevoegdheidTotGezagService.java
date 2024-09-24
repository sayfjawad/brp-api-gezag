package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.openapitools.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private final GezagTransformer gezagTransformer;

    public BevoegdheidTotGezagService(
        final BrpService brpService,
        final GezagService gezagService,
        final GezagTransformer gezagTransformer
    ) {
        this.brpService = brpService;
        this.gezagService = gezagService;
        this.bsnValidator = new BSNValidator();
        this.gezagTransformer = gezagTransformer;
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

    private Persoon bepaalGezagVoorPersoon(final String burgerservicenummer, final Transaction transaction) {
        var gezagsrelaties = Stream
            .concat(
                gezagService.getGezag(List.of(burgerservicenummer), transaction).stream(),
                vindGezagsrelatiesVoorKinderen(burgerservicenummer, transaction)
            )
            .toList();
        var abstractGezagsrelaties = gezagTransformer.from(gezagsrelaties).stream()
            .filter(gezagsrelatie -> bevatPersoon(gezagsrelatie, burgerservicenummer))
            .toList();

        return new Persoon()
            .burgerservicenummer(burgerservicenummer)
            .gezag(abstractGezagsrelaties);
    }

    private Stream<Gezagsrelatie> vindGezagsrelatiesVoorKinderen(final String bevraagdePersoon, final Transaction transaction) throws GezagException {
        List<String> kinderen = brpService.getBsnsMinderjarigeKinderen(bevraagdePersoon, transaction);
        List<Gezagsrelatie> gezagsrelaties = gezagService.getGezag(kinderen, transaction);
        return gezagsrelaties.stream()
            .filter(gezagsrelatie -> gezagsrelatie.isTweehoofdigOuderlijkGezag()
                || gezagsrelatie.isGezamenlijkGezag()
                || bevraagdePersoon.equals(gezagsrelatie.getBsnMeerderjarige())
            );
    }

    private boolean bevatPersoon(
        final AbstractGezagsrelatie abstractGezagsrelatie,
        final String bevraagdePersoonBurgerservicenummer
    ) {
        return switch (abstractGezagsrelatie) {
            case GezamenlijkGezag gezamenlijkGezag -> {
                String minderjarigeBurgerservicenummer = gezamenlijkGezag.getMinderjarige().orElseThrow().getBurgerservicenummer(); // not optional, should be fixed in OpenAPI Spec
                if (minderjarigeBurgerservicenummer.equals(bevraagdePersoonBurgerservicenummer)) yield true;

                var isOuder = gezamenlijkGezag.getOuder()
                    .map(ouder -> ouder.getBurgerservicenummer().equals(bevraagdePersoonBurgerservicenummer))
                    .orElse(false);
                var isDerde = gezamenlijkGezag.getDerde()
                    .map(derde -> derde.getBurgerservicenummer().orElseThrow().equals(bevraagdePersoonBurgerservicenummer)) // not optional, should be fixed in OpenAPI Spec
                    .orElse(false);
                yield isOuder || isDerde;
            }
            case Voogdij voogdij -> {
                String minderjarigeBurgerservicenummer = voogdij.getMinderjarige().orElseThrow().getBurgerservicenummer(); // not optional, should be fixed in OpenAPI Spec
                if (minderjarigeBurgerservicenummer.equals(bevraagdePersoonBurgerservicenummer)) yield true;

                yield voogdij.getDerden().stream()
                    .anyMatch(derde -> derde.getBurgerservicenummer().orElseThrow().equals(bevraagdePersoonBurgerservicenummer));  // not optional, should be fixed in OpenAPI Spec
            }
            default -> true;
        };
    }
}
