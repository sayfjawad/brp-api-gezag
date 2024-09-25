package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.*;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelatie;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelaties;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.service.BeslissingsmatrixService;
import nl.rijksoverheid.mev.gezagsmodule.service.PersoonlijstType;
import nl.rijksoverheid.mev.gezagsmodule.service.ToelichtingService;
import nl.rijksoverheid.mev.gezagsmodule.service.VragenlijstService;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.openapitools.model.AbstractGezagsrelatie;
import org.openapitools.model.GezagNietTeBepalen;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Service voor bepalen gezag
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GezagService {

    private final VragenlijstService vragenlijstService;
    private final TransactionHandler transactionHandler;
    private final BrpService brpService;
    private final BeslissingsmatrixService beslissingsmatrixService;
    private final ToelichtingService toelichtingService;
    private static final String DEFAULT_NEE = "Nee";
    private static final String SOORT_GEZAG_NVT = "NVT";
    private static final String SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD = "N";
    private static final String BSN_MEERDERJARIGE_LEEG = "";

    /**
     * Bepaal gezag van kind
     *
     * @param burgerservicenummers        de burgerservicenummers om gezag voor te bepalen
     * @param burgerservicenummerPersoon  het burgerservicenummer van de persoon waar de gezag bepaling voor plaats vind
     * @param transaction de transactie zoals gemaakt bij het ontvangen van het
     *                    request
     * @return lijst gezagsrelaties of lijst gezagsrelatie 'N'
     */
    public List<AbstractGezagsrelatie> getGezag(final List<String> burgerservicenummers, final String burgerservicenummerPersoon, final Transaction transaction) throws BrpException {
        List<AbstractGezagsrelatie> gezagRelaties = new ArrayList<>();
        for (String burgerservicenummer : burgerservicenummers) {
            try {
                gezagRelaties.addAll(getGezagResultaat(burgerservicenummer, burgerservicenummerPersoon, transaction));
            } catch (AfleidingsregelException ex) {
                log.error("Gezagsrelatie kon niet worden bepaald, dit is een urgent probleem! Resultaat 'N' wordt als antwoord gegeven", ex);
                transactionHandler.saveGezagmoduleTransaction(null, null, null, SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD, null, transaction);
                gezagRelaties.add(new GezagNietTeBepalen().toelichting(
                    "Gezagsrelatie kon niet worden bepaald vanwege een onverwachte exceptie, resultaat 'N' wordt als antwoord gegeven"));
            }
        }

        return gezagRelaties;
    }

    /**
     * Bepaal gezag afleidingsresultaat
     *
     * @param burgerservicenummer         het burgerservicenummers om gezag voor te bepalen
     * @param burgerservicenummerPersoon  het burgerservicenummer van de persoon waar de gezag bepaling voor plaats vind
     * @param transaction originele transactie
     * @return gezagsafleidingsresultaat
     * @throws AfleidingsregelException wanneer gezag niet kan worden bepaald
     */
    public List<AbstractGezagsrelatie> getGezagResultaat(final String burgerservicenummer, final String burgerservicenummerPersoon, final Transaction transaction) throws GezagException {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        List<AbstractGezagsrelatie> gezagRelaties = new ArrayList<>();
        String route;
        Persoonslijst plPersoon = null;
        GezagBepaling gezagBepaling = null;
        try {
            if (new BSNValidator().isValid(burgerservicenummer)) {
                plPersoon = brpService.getPersoonslijst(burgerservicenummer, transaction);
                transactionHandler.saveGezagmoduleTransaction(
                    PersoonlijstType.PERSOON,
                    plPersoon.getReceivedId(),
                    null, null, null, transaction);

                gezagBepaling = new GezagBepaling(plPersoon, this, vragenlijstService.getVragenMap(), transaction);
                arAntwoordenModel = gezagBepaling.start();
            }
        } catch (VeldInOnderzoekException | AfleidingsregelException ex) {
            arAntwoordenModel.setException(ex);
        } catch (BrpException ex) {
            throw new PersoonslijstNotFoundException("Persoonslijst kan niet gevonden worden", ex);
        }
        boolean hasVeldenInOnderzoek = gezagBepaling != null && gezagBepaling.warenVeldenInOnderzoek();
        if (hasVeldenInOnderzoek) {
            arAntwoordenModel.setException(new VeldInOnderzoekException("Preconditie: Velden mogen niet in onderzoek staan"));
        }
        route = beslissingsmatrixService.findMatchingRoute(arAntwoordenModel);
        arAntwoordenModel.setRoute(route);
        setConfiguredValues(arAntwoordenModel);

        String unformattedUitleg = arAntwoordenModel.getUitleg();

        if (hasVeldenInOnderzoek) {
            route = route + "i";
            arAntwoordenModel.setRoute(arAntwoordenModel.getRoute() + "i");
            arAntwoordenModel.setSoortGezag(SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD);
            arAntwoordenModel.setGezagOuder1(DEFAULT_NEE);
            arAntwoordenModel.setGezagOuder2(DEFAULT_NEE);
            arAntwoordenModel.setGezagNietOuder1(DEFAULT_NEE);
            arAntwoordenModel.setGezagNietOuder2(DEFAULT_NEE);
            arAntwoordenModel.setUitleg(toelichtingService.decorateToelichting(unformattedUitleg, gezagBepaling.getVeldenInOnderzoek(), null));
        }

        if (gezagBepaling != null) {
            List<String> missendeGegegevens = gezagBepaling.getMissendeGegegevens();
            UUID errorTraceCode = gezagBepaling.getErrorTraceCode();

            if (!missendeGegegevens.isEmpty()) {
                String toelichting = toelichtingService.decorateToelichting(unformattedUitleg, null, missendeGegegevens);
                arAntwoordenModel.setUitleg(toelichting);
            } else if (errorTraceCode != null) {
                unformattedUitleg = unformattedUitleg.formatted(errorTraceCode.toString());
                arAntwoordenModel.setUitleg(unformattedUitleg);
            }

            gezagBepaling.bepalenGezagdragers(burgerservicenummer, burgerservicenummerPersoon, arAntwoordenModel, gezagRelaties);
        }

        String persoonReceivedId = (plPersoon != null ? plPersoon.getReceivedId() : "");
        transactionHandler.saveGezagmoduleTransaction(
            null,
            persoonReceivedId,
            route,
            arAntwoordenModel.getSoortGezag(),
            (gezagBepaling != null ? gezagBepaling.getVeldenInOnderzoek() : null),
            transaction);
        if (transaction.getReceivedId() == null) {
            transaction.setReceivedId(persoonReceivedId);
        }

        return gezagRelaties;
    }

    /*
     * Als een persoonslijst lokaal aanwezig is, wordt deze opgehaald.
     * Anders wordt deze uit het BRP opgehaald en lokaal opgeslagen.
     * De lokale kopie wordt gebruikt om de aanvullende businesslogica te voorzien van persoonsgegevens
     */

    /**
     * Ophalen ouder1
     *
     * @param plPersoon           de persoon om ouder 1 voor op te halen
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     *                            van het request
     * @return ouder 1 of null
     */
    public Persoonslijst ophalenOuder1(final Persoonslijst plPersoon, final Transaction originalTransaction) {
        Persoonslijst plOuder1 = null;
        try {
            if (plPersoon.getOuder1() != null && plPersoon.getOuder1().getBsn() != null) {
                plOuder1 = brpService.getPersoonslijst(
                    plPersoon.getOuder1().getBsn(), originalTransaction);
                if (plOuder1 != null) {
                    transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.OUDER1,
                        plOuder1.getReceivedId(),
                        null,
                        null,
                        null,
                        originalTransaction);
                    plOuder1.setHopRelaties(new HopRelaties());
                    plOuder1.checkHopRelaties();
                }
            }
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
        }

        return plOuder1;
    }

    /**
     * Ophalen ouder 2
     *
     * @param plPersoon           de persoon om ouder2 voor op te halen
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     *                            van het request
     * @return ouder2 of null
     */
    public Persoonslijst ophalenOuder2(final Persoonslijst plPersoon, final Transaction originalTransaction) {
        Persoonslijst plOuder2 = null;
        try {
            if (plPersoon.getOuder2() != null && plPersoon.getOuder2().getBsn() != null) {
                plOuder2 = brpService.getPersoonslijst(
                    plPersoon.getOuder2().getBsn(), originalTransaction);
                if (plOuder2 != null) {
                    transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.OUDER2,
                        plOuder2.getReceivedId(),
                        null,
                        null,
                        null,
                        originalTransaction);
                    plOuder2.setHopRelaties(new HopRelaties());
                    plOuder2.checkHopRelaties();

                }

            }
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
        }

        return plOuder2;
    }

    /**
     * Ophalen niet-ouder
     *
     * @param plPersoon           de persoon om niet ouder voor te bepalen
     * @param plOuder1            de ouder 1
     * @param plOuder2            de ouder 2
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     *                            van het request entry of the request
     * @return de niet ouder of null
     */
    public Persoonslijst ophalenNietOuder(final Persoonslijst plPersoon, final Persoonslijst plOuder1, final Persoonslijst plOuder2, final Transaction originalTransaction) {
        try {
            if (!isValidPersoon(plPersoon) || !isOneParentPresent(plOuder1, plOuder2)) {
                return null;
            }

            int geboortedatum = Integer.parseInt(plPersoon.getPersoon().getGeboortedatum());
            Persoonslijst ouder = Objects.requireNonNullElse(plOuder1, plOuder2);
            HopRelatie hopGeborenInRelatie = getHopGeborenInRelatie(ouder, geboortedatum);

            if (hopGeborenInRelatie == null) {
                return null;
            }

            Persoonslijst plNietOuder = brpService.getPersoonslijst(hopGeborenInRelatie.getPartner(), originalTransaction);
            if (plNietOuder != null) {
                saveTransaction(plNietOuder, originalTransaction);
            }

            return plNietOuder;
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    private boolean isValidPersoon(Persoonslijst plPersoon) {
        return plPersoon != null && plPersoon.getPersoon() != null && plPersoon.getPersoon().getGeboortedatum() != null;
    }

    private boolean isOneParentPresent(Persoonslijst plOuder1, Persoonslijst plOuder2) {
        return plOuder1 == null ^ plOuder2 == null;
    }

    private HopRelatie getHopGeborenInRelatie(Persoonslijst ouder, int geboortedatum) {
        HopRelaties hopRelaties = ouder.getHopRelaties();
        return hopRelaties != null ? hopRelaties.geborenInRelatie(geboortedatum) : null;
    }

    private void saveTransaction(Persoonslijst plNietOuder, Transaction originalTransaction) {
        transactionHandler.saveGezagmoduleTransaction(
            PersoonlijstType.NIET_OUDER,
            plNietOuder.getReceivedId(),
            null,
            null,
            null,
            originalTransaction
        );
    }

    private void setConfiguredValues(final ARAntwoordenModel arAntwoordenModel) throws AfleidingsregelException {
        ARAntwoordenModel configuredARAntwoordenModel = beslissingsmatrixService.getARAntwoordenModel(arAntwoordenModel);
        arAntwoordenModel.setSoortGezag(configuredARAntwoordenModel.getSoortGezag());
        arAntwoordenModel.setGezagOuder1(configuredARAntwoordenModel.getGezagOuder1());
        arAntwoordenModel.setGezagOuder2(configuredARAntwoordenModel.getGezagOuder2());
        arAntwoordenModel.setGezagNietOuder1(configuredARAntwoordenModel.getGezagNietOuder1());
        arAntwoordenModel.setGezagNietOuder2(configuredARAntwoordenModel.getGezagNietOuder2());
        arAntwoordenModel.setUitleg(configuredARAntwoordenModel.getUitleg());
    }
}
