package nl.rijksoverheid.mev.gezagsmodule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.exception.VeldInOnderzoekException;
import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import nl.rijksoverheid.mev.gezagsmodule.model.GezagAfleidingsResultaat;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service voor bepalen gezag
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GezagService {

    @Value("${exceptie-bij-in-onderzoek}")
    private boolean exceptionWhenInOnderzoek;

    private final VragenlijstService vragenlijstService;
    private final TransactionHandler transactionHandler;
    private final BrpService brpService;
    private final BeslissingsmatrixService beslissingsmatrixService;
    private static final String DEFAULT_NEE = "Nee";
    private static final String SOORT_GEZAG_NVT = "NVT";
    private static final String SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD = "N";
    private static final String BSN_MEERDERJARIGE_LEEG = "";
    private static final String EINDE = "Einde";

    /**
     * Bepaal gezag van kind
     *
     * @param bsnKind de bsn
     * @param transaction de transactie zoals gemaakt bij het ontvangen van het
     * request
     * @return lijst gezagsrelaties of lijst gezagsrelatie 'N'
     */
    public List<Gezagsrelatie> getGezag(final String bsnKind, final Transaction transaction) throws BrpException {
        try {
            return getGezagAfleidingsResultaat(bsnKind, transaction).getGezagsrelaties();
        } catch (AfleidingsregelException ex) {
            log.error("Gezagsrelatie kon niet worden bepaald, dit is een urgent probleem! Resultaat 'N' wordt als antwoord gegeven", ex);

            transactionHandler.saveGezagmoduleTransaction(null, null, null, SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD, null, transaction);

            return List.of(new Gezagsrelatie(bsnKind, SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD, BSN_MEERDERJARIGE_LEEG));
        }
    }

    /**
     * Bepaal gezag afleidingsresultaat
     *
     * @param bsnKind bsn van kind
     * @param transaction originele transactie
     * @return gezagsafleidingsresultaat
     * @throws AfleidingsregelException wanneer gezag niet kan worden bepaald
     */
    public GezagAfleidingsResultaat getGezagAfleidingsResultaat(final String bsnKind, final Transaction transaction) throws GezagException {
        ARVragenModel arVragenModel = null;
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        GezagAfleidingsResultaat result;
        List<Gezagsrelatie> gezagRelaties = new ArrayList<>();
        String route;
        String antwoord;
        Persoonslijst plPersoon = null;

        try {
            if (new BSNValidator().isValid(bsnKind)) {
                plPersoon = brpService.getPersoonslijst(bsnKind, transaction);

                transactionHandler.saveGezagmoduleTransaction(
                        PersoonlijstType.PERSOON,
                        plPersoon.getReceivedId(),
                        null, null, null, transaction);

                Map<String, Map<String, String>> hoofdstroomschema = vragenlijstService.getVragenMap();
                // Implementatie van de logica van de vragenlijst
                String huidigeVraag = "v1.1";
                try {
                    arVragenModel = new ARVragenModel(plPersoon, this, transaction);
                    while (true) {
                        switch (huidigeVraag) {
                            case "v1.1" -> {
                                antwoord = arVragenModel.v1_1_isPersoonIngezeteneInBRP();
                                arAntwoordenModel.setV0101(antwoord);
                            }
                            case "v1.2" -> {
                                antwoord = arVragenModel.v1_2_IsPersoonMinderjarigEnNietOverleden();
                                arAntwoordenModel.setV0102(antwoord);
                            }
                            case "v1.3" -> {
                                antwoord = arVragenModel.v1_3_isNaarHetBuitenlandGeemigreerdGeweest();
                                arAntwoordenModel.setV0103(antwoord);
                            }
                            case "v1.3a" -> {
                                antwoord = arVragenModel.v1_3a_isGeborenInBuitenland();
                                arAntwoordenModel.setV0103A(antwoord);
                            }
                            case "v1.3b" -> {
                                antwoord = arVragenModel.v1_3b_IsGeadopteerdMetNlAkte();
                                arAntwoordenModel.setV0103B(antwoord);
                            }
                            case "v1.4" -> {
                                antwoord = arVragenModel.v1_4_IsUitspraakGezagAanwezig();
                                arAntwoordenModel.setV0104(antwoord);
                            }
                            case "v2.1" -> {
                                antwoord = arVragenModel.v2_1_HoeveelJuridischeOudersHeeftMinderjarige();
                                arAntwoordenModel.setV0201(antwoord);
                            }
                            case "v2a.1" -> {
                                antwoord = arVragenModel.v2a_1_ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners();
                                arAntwoordenModel.setV02A01(antwoord);
                            }
                            case "v2a.2" -> {
                                antwoord = arVragenModel.v2a_2_AdoptiefOuders();
                                arAntwoordenModel.setV02A02(antwoord);
                            }
                            case "v2a.3" -> {
                                antwoord = arVragenModel.v2a_3_ErkenningNa01012023();
                                arAntwoordenModel.setV02A03(antwoord);
                            }
                            case "v2b.1" -> {
                                antwoord = arVragenModel.v2_b_1_IsStaandeHuwelijkOfPartnerschapGeboren();
                                arAntwoordenModel.setV02B01(antwoord);
                            }
                            case "v3.1" -> {
                                antwoord = arVragenModel.v3_1_IsErSprakeVanEenRecenteGebeurtenis();
                                arAntwoordenModel.setV0301(antwoord);
                            }
                            case "v3.2" -> {
                                antwoord = arVragenModel.v3_2_IndicatieGezagMinderjarige();
                                arAntwoordenModel.setV0302(antwoord);
                            }
                            case "v4a.2" -> {
                                antwoord = arVragenModel.v4a_2_OudersOverledenOfOnbevoegdTotGezag();
                                arAntwoordenModel.setV04A02(antwoord);
                            }
                            case "v4a.3" -> {
                                antwoord = arVragenModel.v4a_3_OuderOverledenOfOnbevoegdTotGezag();
                                arAntwoordenModel.setV04A03(antwoord);
                            }
                            case "v4b.1" -> {
                                antwoord = arVragenModel.v4b_1_OuderOfPartnerOverledenOfOnbevoegdTotGezag();
                                arAntwoordenModel.setV04B01(antwoord);
                            }
                            default ->
                                antwoord = EINDE;
                        }

                        // Kijk in het hoofdstroomschema of bij de huidige vraag nog antwoorden zijn die leiden naar een
                        // volgende vraag, zo niet, dan beëindig de while loop
                        Map<String, String> antwoordEnActieParen = hoofdstroomschema.get(huidigeVraag);
                        if (antwoordEnActieParen == null) {
                            break;
                        }
                        // 4. De actie uitvoeren die is gekoppeld aan het antwoord en doorgaan naar de volgende vraag.                        
                        huidigeVraag = antwoordEnActieParen.getOrDefault(antwoord, EINDE);
                    }
                } catch (AfleidingsregelException e) {
                    arAntwoordenModel.setException(e);
                } catch (BrpException e) {
                    throw new BrpException();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (VeldInOnderzoekException | AfleidingsregelException ex) {
            arAntwoordenModel.setException(ex);
        }

        boolean hasVeldenInOnderzoek = arVragenModel != null && arVragenModel.warenVeldenInOnderzoek();
        if (hasVeldenInOnderzoek && exceptionWhenInOnderzoek) {
            arAntwoordenModel.setException(new VeldInOnderzoekException("Preconditie: Velden mogen niet in onderzoek staan"));
        } else {
            // Beslissen wat else
        }

        route = beslissingsmatrixService.findMatchingRoute(arAntwoordenModel);
        arAntwoordenModel.setRoute(route);
        arAntwoordenModel.setSoortGezag(SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD);
        arAntwoordenModel.setGezagOuder1(DEFAULT_NEE);
        arAntwoordenModel.setGezagOuder2(DEFAULT_NEE);
        arAntwoordenModel.setGezagNietOuder1(DEFAULT_NEE);
        arAntwoordenModel.setGezagNietOuder2(DEFAULT_NEE);
        arAntwoordenModel.setUitleg("In onderzoek velden geconstateerd");
        if (!hasVeldenInOnderzoek) {
            setConfiguredValues(arAntwoordenModel);
        }

        Set<String> gezagsdragers = new HashSet<>();
        if (arVragenModel != null) {
            gezagsdragers = arVragenModel.bepalenGezagdragers(arAntwoordenModel);
        }

        if (!gezagsdragers.isEmpty()) {
            gezagRelaties = gezagsdragers.stream().map(gezagdrager -> new Gezagsrelatie(bsnKind,
                    arAntwoordenModel.getSoortGezag(), gezagdrager)).toList();
        } else if (arAntwoordenModel != null && arAntwoordenModel.getSoortGezag() != null && !arAntwoordenModel.getSoortGezag().equals(SOORT_GEZAG_NVT)) {
            gezagRelaties.add(new Gezagsrelatie(bsnKind, arAntwoordenModel.getSoortGezag(), BSN_MEERDERJARIGE_LEEG));
        }

        result = new GezagAfleidingsResultaat(gezagRelaties, arAntwoordenModel, route);

        String persoonReceivedId = (plPersoon != null ? plPersoon.getReceivedId() : "");
        transactionHandler.saveGezagmoduleTransaction(
                null,
                persoonReceivedId,
                route,
                arAntwoordenModel.getSoortGezag(),
                (arVragenModel != null ? arVragenModel.getVeldenInOnderzoek() : null),
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
     * @param plPersoon de persoon om ouder 1 voor op te halen
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     * van het request
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
     * @param plPersoon de persoon om ouder2 voor op te halen
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     * van het request
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
     * @param plPersoon de persoon om niet ouder voor te bepalen
     * @param plOuder1 de ouder 1
     * @param plOuder2 de ouder 2
     * @param originalTransaction de transactie zoals gemaakt bij het ontvangen
     * van het request entry of the request
     * @return de niet ouder of null
     */
    public Persoonslijst ophalenNietOuder(final Persoonslijst plPersoon, final Persoonslijst plOuder1, final Persoonslijst plOuder2, final Transaction originalTransaction) {
        Persoonslijst plNietOuder = null;
        HopRelaties hopRelaties;
        HopRelatie hopGeborenInRelatie;
        HopRelatie hopActueleRelatie;

        try {
            if (plPersoon != null && plPersoon.getPersoon() != null && plPersoon.getPersoon().getGeboortedatum() != null) {
                int geboortedatum = Integer.parseInt(plPersoon.getPersoon().getGeboortedatum());
                if (plOuder1 == null ^ plOuder2 == null) {
                    Persoonslijst nonNullPersoonsLijst = Objects.requireNonNullElse(plOuder1, plOuder2);
                    hopRelaties = nonNullPersoonsLijst.getHopRelaties();
                    if (hopRelaties != null) {
                        hopGeborenInRelatie = hopRelaties.geborenInRelatie(geboortedatum);
                        if (hopGeborenInRelatie != null) {
                            plNietOuder = brpService.getPersoonslijst(
                                    hopGeborenInRelatie.getPartner(), originalTransaction);
                            if (plNietOuder != null) {
                                transactionHandler.saveGezagmoduleTransaction(
                                        PersoonlijstType.NIET_OUDER,
                                        plNietOuder.getReceivedId(),
                                        null,
                                        null,
                                        null,
                                        originalTransaction);
                            }
                        }
                    }
                }
            }
        } catch (GezagException ex) {
            log.debug(ex.getMessage());
        }

        return plNietOuder;
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
