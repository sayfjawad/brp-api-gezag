package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Persoon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final GezagTransformer gezagTransformer;
    private final BSNValidator bsnValidator;

    public BevoegdheidTotGezagService(
        final BrpService brpService,
        final GezagService gezagService,
        final GezagTransformer gezagTransformer
    ) {
        this.brpService = brpService;
        this.gezagService = gezagService;
        this.gezagTransformer = gezagTransformer;
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
        List<String> bsns = gezagRequest.getBurgerservicenummer();
        if (!bsnValidator.isValid(bsns)) {
            return Collections.emptyList();
        }

        List<Gezagsrelatie> gezagsrelaties = new ArrayList<>();
        for (String bevraagdePersoon : bsns) {
            List<Gezagsrelatie> persoonGezagsrelaties = Stream
                .concat(
                    gezagService.getGezag(List.of(bevraagdePersoon), transaction).stream(),
                    vindGezagsrelatiesVoorKinderen(bevraagdePersoon, transaction)
                )
                .toList();
            persoonGezagsrelaties.forEach(gr -> gr.setBsnBevraagdePersoon(bevraagdePersoon));
            gezagsrelaties.addAll(persoonGezagsrelaties);
        }

        return gezagTransformer.fromGezagrelaties(gezagsrelaties);
    }

    private Stream<Gezagsrelatie> vindGezagsrelatiesVoorKinderen(final String bevraagdePersoon, final Transaction transaction) throws GezagException {
        List<String> kinderen = brpService.getBsnsMinderjarigeKinderen(bevraagdePersoon, transaction);
        return gezagService.getGezag(kinderen, transaction).stream()
            .filter(gezagsrelatie -> bevraagdePersoon.equals(gezagsrelatie.getBsnMeerderjarige()));
    }

}