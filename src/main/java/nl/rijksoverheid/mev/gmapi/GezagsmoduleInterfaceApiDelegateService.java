package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.exception.GezagException;
import org.openapitools.api.GezagsmoduleInterfaceApiDelegate;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.GezagResponse;
import org.openapitools.model.Persoon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

/**
 * Service tussen gm api controller en gezag service
 */
@Service
public class GezagsmoduleInterfaceApiDelegateService implements GezagsmoduleInterfaceApiDelegate {

    private final BevoegdheidTotGezagService bevoegdheidTotGezagService;

    public GezagsmoduleInterfaceApiDelegateService(BevoegdheidTotGezagService bevoegdheidTotGezagService) {
        this.bevoegdheidTotGezagService = bevoegdheidTotGezagService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return GezagsmoduleInterfaceApiDelegate.super.getRequest();
    }

    /**
     * Opvragen bevoegdheid to gezag (meerder / minderjarig)
     *
     * @param gezagRequest de aanvraag
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer een
     * exceptie optreed bij BRP communcatie of bij het bepalen van gezag in de
     * gezagmodule
     * @return gezagsrelaties of leeg
     */
    @Override
    public ResponseEntity<GezagResponse> opvragenBevoegdheidTotGezag(final GezagRequest gezagRequest) {
        List<Persoon> gezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(gezagRequest);

        GezagResponse response = new GezagResponse();
        response.setPersonen(gezagsrelaties);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final GezagException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getMessage());
    }
}