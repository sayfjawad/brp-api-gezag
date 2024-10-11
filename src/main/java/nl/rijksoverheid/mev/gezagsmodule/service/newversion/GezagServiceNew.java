package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.exception.VeldInOnderzoekException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelatie;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelaties;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.GezagAfleidingsResultaat;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.*;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service voor bepalen gezag
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.features.refactored-gezag.enabled", havingValue = "true")
public class GezagServiceNew implements GezagService {

    private final VragenlijstService vragenlijstService;
    private final TransactionHandler transactionHandler;
    private final BrpService brpService;
    private final BeslissingsmatrixService beslissingsmatrixService;
    private final ToelichtingService toelichtingService;
    private static final String DEFAULT_NEE = "Nee";
    private static final String SOORT_GEZAG_NVT = "NVT";
    private static final String SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD = "N";
    private static final String BSN_MEERDERJARIGE_LEEG = "";
    private static final String TOELICHTING_ONBEKEND_PERSOON = "Voor het opgegeven burgerservicenummer kon geen persoonslijst worden gevonden";

    /**
     * Bepaal gezag van kind
     *
     * @param bsns        de bsn(s)
     * @param transaction de transactie zoals gemaakt bij het ontvangen van het
     *                    request
     * @return lijst gezagsrelaties of lijst gezagsrelatie 'N'
     */
    @Override
    public List<Gezagsrelatie> getGezag(final List<String> bsns, final Transaction transaction) {
        List<Gezagsrelatie> gezagRelaties = new ArrayList<>();
        for (String bsn : bsns) {
            try {
                gezagRelaties.addAll(getGezagAfleidingsResultaat(bsn, transaction).getGezagsrelaties());
            } catch (AfleidingsregelException ex) {
                log.error("Gezagsrelatie kon niet worden bepaald, dit is een urgent probleem! Resultaat 'N' wordt als antwoord gegeven", ex);
                transactionHandler.saveGezagmoduleTransaction(null, null, null, SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD, null, transaction);
                gezagRelaties.add(new Gezagsrelatie(bsn, SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD, BSN_MEERDERJARIGE_LEEG,
                    "Gezagsrelatie kon niet worden bepaald vanwege een onverwachte exceptie, resultaat 'N' wordt als antwoord gegeven"));
            }
        }

        return gezagRelaties;
    }

    /**
     * Bepaal gezag afleidingsresultaat
     *
     * @param bsn         bsn van kind
     * @param transaction originele transactie
     * @return gezagsafleidingsresultaat
     * @throws AfleidingsregelException wanneer gezag niet kan worden bepaald
     */
    @Override
    public GezagAfleidingsResultaat getGezagAfleidingsResultaat(final String bsn, final Transaction transaction) throws GezagException {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        GezagAfleidingsResultaat result;
        List<Gezagsrelatie> gezagRelaties = new ArrayList<>();
        String route;
        Optional<Persoonslijst> plPersoon = Optional.empty();
        GezagBepaling gezagBepaling = null;
        try {
            if (new BSNValidator().isValid(bsn)) {
                plPersoon = brpService.getPersoonslijst(bsn, transaction);
                if (plPersoon.isPresent()) {
                    Persoonslijst persoon = plPersoon.get();
                    transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.PERSOON,
                        persoon.getReceivedId(),
                        null, null, null, transaction);

                    gezagBepaling = new GezagBepaling(persoon, this, vragenlijstService.getVragenMap(), transaction);
                    arAntwoordenModel = gezagBepaling.start();
                }
            }
        } catch (VeldInOnderzoekException | AfleidingsregelException ex) {
            arAntwoordenModel.setException(ex);
        }
        boolean hasVeldenInOnderzoek = gezagBepaling != null && gezagBepaling.warenVeldenInOnderzoek();
        if (hasVeldenInOnderzoek) {
            arAntwoordenModel.setException(new VeldInOnderzoekException("Preconditie: Velden mogen niet in onderzoek staan"));
        }
        route = beslissingsmatrixService.findMatchingRoute(arAntwoordenModel);
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

            gezagBepaling.bepalenGezagdragers(bsn, arAntwoordenModel, gezagRelaties);
        }

        if (gezagRelaties.isEmpty() && arAntwoordenModel.getSoortGezag() != null && !arAntwoordenModel.getSoortGezag().equals(SOORT_GEZAG_NVT)) {
            String uitleg = arAntwoordenModel.getUitleg();
            gezagRelaties.add(new Gezagsrelatie(bsn, arAntwoordenModel.getSoortGezag(), BSN_MEERDERJARIGE_LEEG, uitleg));
        }

        result = new GezagAfleidingsResultaat(gezagRelaties, arAntwoordenModel, route);

        String persoonReceivedId = (plPersoon.isPresent() ? plPersoon.get().getReceivedId() : "");
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

        return result;
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
    @Override
    public Persoonslijst ophalenOuder1(final Persoonslijst plPersoon, final Transaction originalTransaction) {
        Optional<Persoonslijst> plOuder1 = Optional.empty();
        try {
            if (plPersoon.getOuder1() != null && plPersoon.getOuder1().getBsn() != null) {
                plOuder1 = brpService.getPersoonslijst(
                    plPersoon.getOuder1().getBsn(), originalTransaction);
                plOuder1.ifPresent((ouder1) -> {
                    transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.OUDER1,
                        ouder1.getReceivedId(),
                        null,
                        null,
                        null,
                        originalTransaction);
                    ouder1.setHopRelaties(new HopRelaties());
                    ouder1.checkHopRelaties();
                });
            }
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
        }

        return plOuder1.orElse(null);
    }

    /**
     * Ophalen ouder 2
     *
     * @param plPersoon           de persoon om ouder2 voor op te halen
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     *                            van het request
     * @return ouder2 of null
     */
    @Override
    public Persoonslijst ophalenOuder2(final Persoonslijst plPersoon, final Transaction originalTransaction) {
        Optional<Persoonslijst> plOuder2 = Optional.empty();
        try {
            if (plPersoon.getOuder2() != null && plPersoon.getOuder2().getBsn() != null) {
                plOuder2 = brpService.getPersoonslijst(
                    plPersoon.getOuder2().getBsn(), originalTransaction);
                plOuder2.ifPresent((ouder2) -> {
                    transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.OUDER2,
                        ouder2.getReceivedId(),
                        null,
                        null,
                        null,
                        originalTransaction);
                    ouder2.setHopRelaties(new HopRelaties());
                    ouder2.checkHopRelaties();
                });
            }
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
        }

        return plOuder2.orElse(null);
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
    @Override
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

            Optional<Persoonslijst> plNietOuder = brpService.getPersoonslijst(hopGeborenInRelatie.getPartner(), originalTransaction);
            plNietOuder.ifPresent((nietOuder) -> {
                saveTransaction(nietOuder, originalTransaction);
            });

            return plNietOuder.orElse(null);
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

    private void setConfiguredValues(final ARAntwoordenModel arAntwoordenModel, final boolean persoonslijstExists) throws AfleidingsregelException {
        ARAntwoordenModel configuredARAntwoordenModel = beslissingsmatrixService.getARAntwoordenModel(arAntwoordenModel);
        arAntwoordenModel.setSoortGezag(configuredARAntwoordenModel.getSoortGezag());
        arAntwoordenModel.setGezagOuder1(configuredARAntwoordenModel.getGezagOuder1());
        arAntwoordenModel.setGezagOuder2(configuredARAntwoordenModel.getGezagOuder2());
        arAntwoordenModel.setGezagNietOuder1(configuredARAntwoordenModel.getGezagNietOuder1());
        arAntwoordenModel.setGezagNietOuder2(configuredARAntwoordenModel.getGezagNietOuder2());
        arAntwoordenModel.setUitleg((!persoonslijstExists ? TOELICHTING_ONBEKEND_PERSOON :
            configuredARAntwoordenModel.getUitleg()));
    }
}
