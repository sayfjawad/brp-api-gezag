package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Gezagsrelatie;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public BevoegdheidTotGezagService(BrpService brpService, GezagService gezagService) {
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
     * @param gezagRequest <i>burgerservicenummer</i> van de persoon van wie de
     * <i>bevoegdheid tot gezag</i> bepaald moet worden
     * @param transaction de transactie zoals gemaakt bij het ontvangen
     * van de aanvraag
     * @return 0, 1 of meerdere gezagsrelaties
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer onverwacht
     * het gezag niet kan worden bepaald
     */
    public List<Gezagsrelatie> bepaalBevoegdheidTotGezag(
        final GezagRequest gezagRequest,
        final Transaction transaction
    ) throws GezagException {
        String bsn = gezagRequest.getBsn();
        if (!bsnValidator.isValid(bsn)) return Collections.emptyList();

        return Stream
            .concat(
                gezagService.getGezag(bsn, transaction).stream().map(Gezagsrelaties::of),
                vindGezagsrelatiesVoorKinderen(bsn, transaction)
            )
            .toList();
    }

    /**
     * Bepaal <i>bevoegdheid tot gezag</i> over de minderjarige juridische
     * kinderen van een persoon.
     *
     * @param gezagRequest <i>burgerservicenummer</i> van de persoon van wie de
     * <i>bevoegdheid tot gezag</i> over diens minderjarige juridische kinderen
     * bepaald moet worden
     * @param transaction de transactie zoals gemaakt bij het ontvangen
     * van de aanvraag
     * @return 0, 1 of meer gezagsrelaties
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer onverwacht
     * het gezag niet kan worden bepaald
     */
    public List<Gezagsrelatie> bepaalBevoegdheidTotGezagMeerderjarige(final GezagRequest gezagRequest, final Transaction transaction) throws GezagException {
        String bsnMeerderjarige = gezagRequest.getBsn();
        if (bsnValidator.isValid(bsnMeerderjarige)) {
            return vindGezagsrelatiesVoorKinderen(bsnMeerderjarige, transaction)
                    .filter(gezagsrelatie -> bsnMeerderjarige.equals(gezagsrelatie.getBsnMeerderjarige()))
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Bepaal de <i>bevoegdheid tot gezag</i> over een minderjarige.
     *
     * @param gezagRequest <i>burgerservicenummer</i> van de minderjarige
     * waarover de <i>bevoegdheid tot gezag</i> bepaald moet worden
     * @param transaction de transactie zoals gemaakt bij het ontvangen
     * van de aanvraag
     * @return 0, 1 of meer gezagsrelaties
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer onverwacht
     * het gezag niet kan worden bepaald
     */
    public List<Gezagsrelatie> bepaalBevoegdheidTotGezagMinderjarige(final GezagRequest gezagRequest, final Transaction transaction) throws GezagException {
        String bsnMinderjarige = gezagRequest.getBsn();
        if (bsnValidator.isValid(bsnMinderjarige)) {
           return gezagService.getGezag(bsnMinderjarige, transaction).stream()
               .map(Gezagsrelaties::of)
               .toList();
        } else {
            return Collections.emptyList();
        }
        
    }

    private Stream<Gezagsrelatie> vindGezagsrelatiesVoorKinderen(final String bsnPersoon, final Transaction transaction) throws GezagException {
        return brpService.getBsnsMinderjarigeKinderen(bsnPersoon, transaction).stream()
            .map(bsn -> gezagService.getGezag(bsn, transaction))
            .flatMap(Collection::stream)
            .map(Gezagsrelaties::of)
            .filter(gezagsrelatie -> gezagsrelatie.getBsnMeerderjarige().equals(bsnPersoon));
    }
}
