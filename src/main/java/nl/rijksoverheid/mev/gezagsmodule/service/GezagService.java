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
import nl.rijksoverheid.mev.gezagsmodule.service.newversion.GezagBepaling;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

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
    private static final String DEFAULT_NEE = "Nee";
    private static final String SOORT_GEZAG_NVT = "NVT";
    private static final String SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD = "N";
    private static final String BSN_MEERDERJARIGE_LEEG = "";
    private static final String EINDE = "Einde";

    /**
     * Bepaal gezag van kind
     *
     * @param bsns        de bsn(s)
     * @param transaction de transactie zoals gemaakt bij het ontvangen van het
     *                    request
     * @return lijst gezagsrelaties of lijst gezagsrelatie 'N'
     */
    public List<Gezagsrelatie> getGezag(final List<String> bsns, final Transaction transaction) throws BrpException {
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

    private Map<String, Supplier<String>> initializeVragenMap(ARVragenModel arVragenModel, ARAntwoordenModel arAntwoordenModel) {
        Map<String, Supplier<String>> map = new HashMap<>();
        map.put("v1.1", () -> {
            String antwoord = arVragenModel.v11isPersoonIngezeteneInBRP();
            arAntwoordenModel.setV0101(antwoord);
            return antwoord;
        });
        map.put("v1.2", () -> {
            String antwoord = arVragenModel.v12IsPersoonMinderjarigEnNietOverleden();
            arAntwoordenModel.setV0102(antwoord);
            return antwoord;
        });
        map.put("v1.3", () -> {
            String antwoord = arVragenModel.v13isNaarHetBuitenlandGeemigreerdGeweest();
            arAntwoordenModel.setV0103(antwoord);
            return antwoord;
        });
        map.put("v1.3a", () -> {
            String antwoord = arVragenModel.v13aisGeborenInBuitenland();
            arAntwoordenModel.setV0103A(antwoord);
            return antwoord;
        });
        map.put("v1.3b", () -> {
            String antwoord = arVragenModel.v13bIsGeadopteerdMetNlAkte();
            arAntwoordenModel.setV0103B(antwoord);
            return antwoord;
        });
        map.put("v1.4", () -> {
            String antwoord = arVragenModel.v14IsUitspraakGezagAanwezig();
            arAntwoordenModel.setV0104(antwoord);
            return antwoord;
        });
        map.put("v2.1", () -> {
            String antwoord = arVragenModel.v21HoeveelJuridischeOudersHeeftMinderjarige();
            arAntwoordenModel.setV0201(antwoord);
            return antwoord;
        });
        map.put("v2a.1", () -> {
            String antwoord = arVragenModel.v2a1ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners();
            arAntwoordenModel.setV02A01(antwoord);
            return antwoord;
        });
        map.put("v2a.2", () -> {
            String antwoord = arVragenModel.v2a2AdoptiefOuders();
            arAntwoordenModel.setV02A02(antwoord);
            return antwoord;
        });
        map.put("v2a.3", () -> {
            String antwoord = arVragenModel.v2a3ErkenningNa01012023();
            arAntwoordenModel.setV02A03(antwoord);
            return antwoord;
        });
        map.put("v2b.1", () -> {
            String antwoord = arVragenModel.v2b1IsStaandeHuwelijkOfPartnerschapGeboren();
            arAntwoordenModel.setV02B01(antwoord);
            return antwoord;
        });
        map.put("v3.1", () -> {
            String antwoord = arVragenModel.v31IsErSprakeVanEenRecenteGebeurtenis();
            arAntwoordenModel.setV0301(antwoord);
            return antwoord;
        });
        map.put("v3.2", () -> {
            String antwoord = arVragenModel.v32IndicatieGezagMinderjarige();
            arAntwoordenModel.setV0302(antwoord);
            return antwoord;
        });
        map.put("v4a.2", () -> {
            String antwoord = arVragenModel.v4a2OudersOverledenOfOnbevoegdTotGezag();
            arAntwoordenModel.setV04A02(antwoord);
            return antwoord;
        });
        map.put("v4a.3", () -> {
            String antwoord = arVragenModel.v4a3OuderOverledenOfOnbevoegdTotGezag();
            arAntwoordenModel.setV04A03(antwoord);
            return antwoord;
        });
        map.put("v4b.1", () -> {
            String antwoord = arVragenModel.v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag();
            arAntwoordenModel.setV04B01(antwoord);
            return antwoord;
        });
        return map;
    }

    public String processVraag(Map<String, Supplier<String>> vragenMap, String vraagCode) {
        Supplier<String> vraagProcessor = vragenMap.get(vraagCode);
        if (vraagProcessor != null) {
            return vraagProcessor.get();
        } else {
            return "EINDE";
        }
    }

    public void processAlleVragen(ARVragenModel arVragenModel, ARAntwoordenModel arAntwoordenModel) throws BrpException {
        try {
            String huidigeVraag = "v1.1";
            Map<String, Map<String, String>> hoofdstroomschema = vragenlijstService.getVragenMap();
            Map<String, Supplier<String>> vragenMap = initializeVragenMap(arVragenModel, arAntwoordenModel);
            String antwoord;
            while (!huidigeVraag.equals(EINDE)) {
                antwoord = processVraag(vragenMap, huidigeVraag);
                // Logica om de volgende vraag te bepalen uit het hoofdstroomschema
                Map<String, String> antwoordEnActieParen = hoofdstroomschema.get(huidigeVraag);
                if (antwoordEnActieParen == null) {
                    huidigeVraag = EINDE;
                } else {
                    // 4. De actie uitvoeren die is gekoppeld aan het antwoord en doorgaan naar de volgende vraag.
                    huidigeVraag = antwoordEnActieParen.getOrDefault(antwoord, EINDE);
                }
            }
        } catch (AfleidingsregelException e) {
            arAntwoordenModel.setException(e);
        } catch (BrpException e) {
            throw new BrpException();
        }
    }

    /**
     * Bepaal gezag afleidingsresultaat
     *
     * @param bsn         bsn van kind
     * @param transaction originele transactie
     * @return gezagsafleidingsresultaat
     * @throws AfleidingsregelException wanneer gezag niet kan worden bepaald
     */
    public GezagAfleidingsResultaat getGezagAfleidingsResultaat(final String bsn, final Transaction transaction) throws GezagException {
        ARVragenModel arVragenModel = null;
        final ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        GezagAfleidingsResultaat result;
        List<Gezagsrelatie> gezagRelaties = new ArrayList<>();
        String route;
        Persoonslijst plPersoon = null;
        GezagBepaling gezagBepaling = null;
        try {
            if (new BSNValidator().isValid(bsn)) {
                plPersoon = brpService.getPersoonslijst(bsn, transaction);
                transactionHandler.saveGezagmoduleTransaction(
                    PersoonlijstType.PERSOON,
                    plPersoon.getReceivedId(),
                    null, null, null, transaction);
                // Implementatie van de logica van de vragenlijst
                arVragenModel = new ARVragenModel(plPersoon, this, transaction);

                // new
                gezagBepaling = new GezagBepaling(plPersoon, this, vragenlijstService.getVragenMap(), transaction);


                processAlleVragen(arVragenModel, arAntwoordenModel);
            }
        } catch (VeldInOnderzoekException | AfleidingsregelException ex) {
            arAntwoordenModel.setException(ex);
        }
        boolean hasVeldenInOnderzoek = arVragenModel != null && arVragenModel.warenVeldenInOnderzoek();
        if (hasVeldenInOnderzoek) {
            arAntwoordenModel.setException(new VeldInOnderzoekException("Preconditie: Velden mogen niet in onderzoek staan"));
        }
        route = beslissingsmatrixService.findMatchingRoute(arAntwoordenModel);

        if(gezagBepaling != null) {
            String routeNieuw = beslissingsmatrixService.findMatchingRoute(gezagBepaling.getArAntwoordenModel());
            if (route.equals(routeNieuw)) {
                System.out.println("EQUALS");
            } else {
                System.out.println("NOT EQUAL");
                System.out.println(arAntwoordenModel);
                System.out.println(gezagBepaling.getArAntwoordenModel());
            }
        }

        arAntwoordenModel.setRoute(route);
        setConfiguredValues(arAntwoordenModel);

        if (hasVeldenInOnderzoek) {
            route = route + "i";
            arAntwoordenModel.setRoute(arAntwoordenModel.getRoute() + "i");
            arAntwoordenModel.setSoortGezag(SOORT_GEZAG_KAN_NIET_WORDEN_BEPAALD);
            arAntwoordenModel.setGezagOuder1(DEFAULT_NEE);
            arAntwoordenModel.setGezagOuder2(DEFAULT_NEE);
            arAntwoordenModel.setGezagNietOuder1(DEFAULT_NEE);
            arAntwoordenModel.setGezagNietOuder2(DEFAULT_NEE);
            updateUitlegWithInOnderzoek(arAntwoordenModel, arVragenModel);
        }

        Set<String> gezagsdragers = new HashSet<>();
        if (arVragenModel != null) {
            gezagsdragers = arVragenModel.bepalenGezagdragers(arAntwoordenModel);
        }
        if (!gezagsdragers.isEmpty()) {
            gezagRelaties = gezagsdragers.stream().map(gezagdrager -> new Gezagsrelatie(bsn,
                arAntwoordenModel.getSoortGezag(), gezagdrager, arAntwoordenModel.getUitleg())).toList();
        } else if (arAntwoordenModel.getSoortGezag() != null && !arAntwoordenModel.getSoortGezag().equals(SOORT_GEZAG_NVT)) {
            gezagRelaties.add(new Gezagsrelatie(bsn, arAntwoordenModel.getSoortGezag(), BSN_MEERDERJARIGE_LEEG, arAntwoordenModel.getUitleg()));
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

    private void updateUitlegWithInOnderzoek(final ARAntwoordenModel arAntwoordenModel, final ARVragenModel vragenModel) {
        StringBuilder sb = new StringBuilder();
        sb.append(arAntwoordenModel.getUitleg());
        sb.append("Uitspraak is gezag niet te bepalen, omdat er bij de gezagbepaling waardes in onderzoek waren gedetecteerd. Bij het bepalen van gezag werd het volgende veld gebruikt dat in onderzoek staat: \n ");
        VeldenInOnderzoek veldenInOnderzoek = vragenModel.getVeldenInOnderzoek();
        List<String> persoonInOnderzoekVelden = veldenInOnderzoek.getPersoon();
        if (!persoonInOnderzoekVelden.isEmpty()) {
            sb.append("Persoonsvelden: ");
            sb.append(String.join(", ", persoonInOnderzoekVelden));
            sb.append(".\n");
        }
        List<String> ouder1InOnderzoekVelden = veldenInOnderzoek.getOuder1();
        if (!ouder1InOnderzoekVelden.isEmpty()) {
            sb.append(" Velden van ouder 1: ");
            sb.append(String.join(", ", ouder1InOnderzoekVelden));
            sb.append(".\n");
        }
        List<String> ouder2InOnderzoekVelden = veldenInOnderzoek.getOuder2();
        if (!ouder2InOnderzoekVelden.isEmpty()) {
            sb.append(" Velden van ouder 2: ");
            sb.append(String.join(", ", ouder2InOnderzoekVelden));
            sb.append(".\n");
        }
        List<String> nietOuderInOnderzoekVelden = veldenInOnderzoek.getNietOuder();
        if (!nietOuderInOnderzoekVelden.isEmpty()) {
            sb.append(" Velden van niet ouder: ");
            sb.append(String.join(", ", nietOuderInOnderzoekVelden));
            sb.append(".\n");
        }

        arAntwoordenModel.setUitleg(sb.toString());
    }
}
