package nl.rijksoverheid.mev.gezagsmodule.service;

import lombok.RequiredArgsConstructor;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.exception.VeldInOnderzoekException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Leeftijd;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import nl.rijksoverheid.mev.logging.LoggingContext;
import org.openapitools.model.AbstractGezagsrelatie;
import org.openapitools.model.GezagNietTeBepalen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service voor bepalen gezag
 */
@Service
@RequiredArgsConstructor
public class GezagService {
    private static final Logger logger = LoggerFactory.getLogger(GezagService.class);
    private static final String DEFAULT_NEE = "Nee";
    private static final String SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD = "N";
    private static final String ROUTE_MEERDERJARIG = "2m";
    private static final String TOELICHTING_ONBEKEND_PERSOON = "Voor het opgegeven burgerservicenummer kon geen persoonslijst worden gevonden";

    private final GezagsrelatieService gezagsrelatieService;
    private final VragenlijstService vragenlijstService;
    private final BrpService brpService;
    private final BeslissingsmatrixService beslissingsmatrixService;
    private final LoggingContext loggingContext;
    private final ToelichtingService toelichtingService;

    /**
     * Bepaal gezag van kind
     *
     * @param burgerservicenummers       de burgerservicenummers om gezag voor te bepalen
     * @param burgerservicenummerPersoon het burgerservicenummer van de persoon waar de gezag bepaling voor plaats vind
     * @return lijst gezagsrelaties of lijst gezagsrelatie 'N'
     */
    public List<AbstractGezagsrelatie> getGezag(final Set<String> burgerservicenummers, final String burgerservicenummerPersoon) {
        List<AbstractGezagsrelatie> gezagsRelaties = new ArrayList<>();
        for (String burgerservicenummer : burgerservicenummers) {
            try {
                gezagsRelaties.addAll(getGezagResultaat(burgerservicenummer, burgerservicenummerPersoon));
            } catch (AfleidingsregelException ex) {
                logger.error("Gezagsrelatie kon niet worden bepaald, dit is een urgent probleem! Resultaat 'N' wordt als antwoord gegeven", ex);
                gezagsRelaties.add(new GezagNietTeBepalen().toelichting(
                    "Gezagsrelatie kon niet worden bepaald vanwege een onverwachte exceptie, resultaat 'N' wordt als antwoord gegeven"));
            }
        }

        return gezagsRelaties;
    }

    /**
     * Bepaal gezag afleidingsresultaat
     *
     * @param burgerservicenummer        het burgerservicenummers om gezag voor te bepalen
     * @param burgerservicenummerPersoon het burgerservicenummer van de persoon waar de gezag bepaling voor plaats vind
     * @return gezagsafleidingsresultaat
     * @throws AfleidingsregelException wanneer gezag niet kan worden bepaald
     */
    public List<AbstractGezagsrelatie> getGezagResultaat(final String burgerservicenummer, final String burgerservicenummerPersoon) throws GezagException {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        List<AbstractGezagsrelatie> gezagsRelaties = new ArrayList<>();
        String route = null;
        Optional<Persoonslijst> plPersoon = Optional.empty();
        GezagsBepaling gezagsBepaling = null;
        try {
            plPersoon = brpService.getPersoonslijst(burgerservicenummer);
            if (plPersoon.isPresent()) {
                Persoonslijst persoonslijst = plPersoon.get();
                Persoon persoon = persoonslijst.getPersoon();
                if (persoon != null && Leeftijd.of(persoon.getGeboortedatum()).isMinderjarig()) {
                    gezagsBepaling = new GezagsBepaling(burgerservicenummer, burgerservicenummerPersoon, persoonslijst, brpService, vragenlijstService.getVragenMap());
                    arAntwoordenModel = gezagsBepaling.start();
                } else {
                    route = ROUTE_MEERDERJARIG;
                }
            }
        } catch (AfleidingsregelException ex) {
            arAntwoordenModel.setException(ex);
        }
        boolean hasVeldenInOnderzoek = gezagsBepaling != null && gezagsBepaling.warenVeldenInOnderzoek();
        if (hasVeldenInOnderzoek) {
            arAntwoordenModel.setException(new VeldInOnderzoekException("Preconditie: Velden mogen niet in onderzoek staan"));
        }
        route = (route == null ? beslissingsmatrixService.findMatchingRoute(arAntwoordenModel, gezagsBepaling) : route);
        arAntwoordenModel.setRoute(route);
        setConfiguredValues(arAntwoordenModel, plPersoon.isPresent());

        String unformattedUitleg = arAntwoordenModel.getUitleg();

        if (hasVeldenInOnderzoek) {
            route = route + "i";
            arAntwoordenModel.setRoute(arAntwoordenModel.getRoute() + "i");
            arAntwoordenModel.setSoortGezag(SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD);
            arAntwoordenModel.setGezagOuder1(DEFAULT_NEE);
            arAntwoordenModel.setGezagOuder2(DEFAULT_NEE);
            arAntwoordenModel.setGezagNietOuder1(DEFAULT_NEE);
            arAntwoordenModel.setUitleg(toelichtingService.decorateToelichting(unformattedUitleg, gezagsBepaling.getVeldenInOnderzoek(), null));
        }

        if (gezagsBepaling != null) {
            List<String> missendeGegegevens = gezagsBepaling.getMissendeGegegevens();
            UUID errorTraceCode = gezagsBepaling.getErrorTraceCode();

            if (errorTraceCode != null) {
                String toelichting = toelichtingService.setErrorReferenceToelichting(unformattedUitleg, errorTraceCode.toString());
                arAntwoordenModel.setUitleg(toelichting);
            } else if (!missendeGegegevens.isEmpty()) {
                String toelichting = toelichtingService.decorateToelichting(unformattedUitleg, null, missendeGegegevens);
                arAntwoordenModel.setUitleg(toelichting);
            }

            gezagsRelaties = gezagsrelatieService.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);
        }

        loggingContext.addGezagType(arAntwoordenModel.getSoortGezag(), burgerservicenummer);
        loggingContext.addRoute(route, burgerservicenummer);
        loggingContext.addToelichting(arAntwoordenModel.getUitleg(), burgerservicenummer);
        logger.info("Gezag bepaald voor persoon \"{}\": {}", burgerservicenummer, arAntwoordenModel);
        return gezagsRelaties;
    }

    private void setConfiguredValues(final ARAntwoordenModel arAntwoordenModel, final boolean persoonslijstExists) throws AfleidingsregelException {
        ARAntwoordenModel configuredARAntwoordenModel = beslissingsmatrixService.getARAntwoordenModel(arAntwoordenModel);
        arAntwoordenModel.setSoortGezag(configuredARAntwoordenModel.getSoortGezag());
        arAntwoordenModel.setGezagOuder1(configuredARAntwoordenModel.getGezagOuder1());
        arAntwoordenModel.setGezagOuder2(configuredARAntwoordenModel.getGezagOuder2());
        arAntwoordenModel.setGezagNietOuder1(configuredARAntwoordenModel.getGezagNietOuder1());
        arAntwoordenModel.setUitleg((!persoonslijstExists ? TOELICHTING_ONBEKEND_PERSOON :
            configuredARAntwoordenModel.getUitleg()));
    }
}
