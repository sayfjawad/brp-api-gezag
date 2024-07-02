package nl.rijksoverheid.mev.persoonsgegevens;

import io.micrometer.core.annotation.Timed;
import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.openapitools.api.PersoonsgegevensV1ApiDelegate;
import org.openapitools.model.Persoonsgegevens;
import org.openapitools.model.PersoonsgegevensRequest;
import org.openapitools.model.PersoonsgegevensResultaaat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
@Timed(
        percentiles = {95., 90., 50.},
        histogram = true,
        description = "Timed metrics for Persoonsgegevens API"
)
public class PersoonsgegevensV1ApiDelegateService implements PersoonsgegevensV1ApiDelegate {

    private static final Logger logger = LoggerFactory.getLogger(PersoonsgegevensV1ApiDelegateService.class);

    private final BrpService brpService;
    private final GezagService gezagService;
    private final TransactionHandler transctionHandler;

    public PersoonsgegevensV1ApiDelegateService(final BrpService brpService, final GezagService gezagService, final TransactionHandler transctionHandler) {
        this.brpService = brpService;
        this.gezagService = gezagService;
        this.transctionHandler = transctionHandler;
    }

    @Override
    public ResponseEntity<PersoonsgegevensResultaaat> getPersoonsgegevens(final PersoonsgegevensRequest persoonsgegevensRequest) {
        logger.info("Determining persoonsgegevens");

        var vertegenwoordigde = persoonsgegevensRequest.getVertegenwoordigde();
        var vertegenwoordiger = persoonsgegevensRequest.getVertegenwoordiger();
        if (vertegenwoordiger == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        var httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Transaction transaction = transctionHandler.createPersoonsgegevensTransaction(httpServletRequest, httpServletResponse);

        try {
            List<Persoonsgegevens> persoonsgegevensToReturn = new ArrayList<>();
            if (vertegenwoordigde != null
                    && vertegenwoordigde.getIdentificatieNummer() != null
                    && !vertegenwoordigde.getIdentificatieNummer().isEmpty()) {
                var bsnVertegenwoordigde = vertegenwoordigde.getIdentificatieNummer();
                var gezagsrelaties = gezagService.getGezag(bsnVertegenwoordigde, transaction);
                var bsnVertegenwoordiger = vertegenwoordiger.getIdentificatieNummer();

                gezagsrelaties.stream()
                    .map(Gezagsrelatie::bsnMeerderjarige)
                    .filter(bsnVertegenwoordiger::equals)
                    .findAny()
                    .ifPresent(unused -> {
                        Persoonslijst persoonLijst = brpService.getPersoonsgegevens(bsnVertegenwoordigde, transaction);
                        var persoonsgegevens = PersoongegevensExtensions.of(persoonLijst);
                        persoonsgegevensToReturn.add(persoonsgegevens);
                    });
            } else {
                var kinderen = brpService
                        .getKinderen(vertegenwoordiger.getIdentificatieNummer(), transaction).stream()
                        .map(PersoongegevensExtensions::of)
                        .toList();
                
                persoonsgegevensToReturn.addAll(kinderen);
            }
            transctionHandler.updateTransaction(HttpStatus.OK, transaction, httpServletRequest);

            return ResponseEntity.ok(new PersoonsgegevensResultaaat()
                    .persoonsgegevensList(persoonsgegevensToReturn));
        } catch (GezagException e) {
            transctionHandler.updateTransaction(e.getStatusCode(), transaction, httpServletRequest);

            throw e;
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(GezagException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getMessage());
    }
}
