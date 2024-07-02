package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.openapitools.api.GezagsmoduleInterfaceApiDelegate;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Gezagsrelatie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Service tussen gm api controller en gezag service
 */
@Service
public class GezagsmoduleInterfaceApiDelegateService implements GezagsmoduleInterfaceApiDelegate {

    private final BevoegdheidTotGezagService bevoegdheidTotGezagService;
    private final TransactionHandler transactionHandler;

    public GezagsmoduleInterfaceApiDelegateService(
            BevoegdheidTotGezagService bevoegdheidTotGezagService,
            TransactionHandler transactionHandler
    ) {
        this.bevoegdheidTotGezagService = bevoegdheidTotGezagService;
        this.transactionHandler = transactionHandler;
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
    public ResponseEntity<List<Gezagsrelatie>> opvragenBevoegdheidTotGezag(final GezagRequest gezagRequest) {
        var httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Transaction transaction = transactionHandler.createGmApiTransaction(httpServletRequest, httpServletResponse);

        try {
            List<Gezagsrelatie> gezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

            transactionHandler.updateTransaction(HttpStatus.OK, transaction, httpServletRequest);

            return ResponseEntity.ok(gezagsrelaties);
        } catch (GezagException e) {
            transactionHandler.updateTransaction(e.getStatusCode(), transaction, httpServletRequest);

            throw e;
        }
    }

    /**
     * Opvragen bevoegdheid to gezag meerderjarige
     *
     * @param gezagRequest de aanvraag
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer een
     * exceptie optreed bij BRP communcatie of bij het bepalen van gezag in de
     * gezagmodule
     * @return gezagrelaties of leeg
     */
    @Override
    public ResponseEntity<List<Gezagsrelatie>> opvragenBevoegdheidTotGezagMeerderjarige(final GezagRequest gezagRequest) {
        var httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Transaction transaction = transactionHandler.createGmApiTransaction(httpServletRequest, httpServletResponse);

        try {
            List<Gezagsrelatie> gezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMeerderjarige(gezagRequest, transaction);

            transactionHandler.updateTransaction(HttpStatus.OK, transaction, httpServletRequest);

            return ResponseEntity.ok(gezagsrelaties);
        } catch (GezagException e) {
            transactionHandler.updateTransaction(e.getStatusCode(), transaction, httpServletRequest);

            throw e;
        }
    }

    /**
     * Opvragen bevoegdheid to gezag minderjarige
     *
     * @param gezagRequest de aanvraag
     * @throws nl.rijksoverheid.mev.exception.GezagException wanneer een
     * exceptie optreed bij BRP communcatie of bij het bepalen van gezag in de
     * gezagmodule
     * @return gezagrelaties of leeg
     */
    @Override
    public ResponseEntity<List<Gezagsrelatie>> opvragenBevoegdheidTotGezagMinderjarige(final GezagRequest gezagRequest) {
        var httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Transaction transaction = transactionHandler.createGmApiTransaction(httpServletRequest, httpServletResponse);

        try {
            List<Gezagsrelatie> gezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMinderjarige(gezagRequest, transaction);

            transactionHandler.updateTransaction(HttpStatus.OK, transaction, httpServletRequest);

            return ResponseEntity.ok(gezagsrelaties);
        } catch (GezagException e) {
            transactionHandler.updateTransaction(e.getStatusCode(), transaction, httpServletRequest);

            throw e;
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final GezagException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getMessage());
    }
}
