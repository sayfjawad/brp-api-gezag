package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import lombok.Getter;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import nl.rijksoverheid.mev.gezagsmodule.service.BrpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Gezag bepaling bevat alle informatie voor een gezag bepaling voor een persoon
 */
public class GezagsBepaling {

    private static final Logger logger = LoggerFactory.getLogger(GezagsBepaling.class);
    private static final Set<String> TE_NEGEREN_VELDEN_IN_ONDERZOEK = Set.of(
        "burgerservicenummer van persoon",
        "gemeente van inschrijving"
    );

    @Getter
    private UUID errorTraceCode;
    @Getter
    private final Persoonslijst plPersoon;
    private Persoonslijst plOuder1;
    private Persoonslijst plOuder2;
    private Persoonslijst plNietOuder;
    @Getter
    private List<String> missendeGegegevens;
    @Getter
    private final String burgerservicenummer;
    @Getter
    private final String burgerservicenummerPersoon;
    private final BrpService brpService;
    private final Map<String, Map<String, String>> hoofdstroomschema;
    private Map<String, GezagVraag> vragenMap;
    @Getter
    private final ARAntwoordenModel arAntwoordenModel;

    public GezagsBepaling(
        final String burgerservicenummer,
        final String burgerservicenummerPersoon,
        final Persoonslijst plPersoon,
        final BrpService brpService,
        final Map<String, Map<String, String>> hoofdstroomschema
    ) {
        this.burgerservicenummer = burgerservicenummer;
        this.burgerservicenummerPersoon = burgerservicenummerPersoon;
        this.plPersoon = plPersoon;
        this.brpService = brpService;
        this.hoofdstroomschema = hoofdstroomschema;

        arAntwoordenModel = new ARAntwoordenModel();
        vragenMap = new HashMap<>();
        missendeGegegevens = new ArrayList<>();

        initializeVragenMap();
    }

    /**
     * Start de gezag bepaling
     */
    public ARAntwoordenModel start() {
        vragenMap.get("v1.1").step();
        return arAntwoordenModel;
    }

    public void next(final String currentQuestion, final String answer) {
        try {
            Map<String, String> antwoordEnActieParen = hoofdstroomschema.get(currentQuestion);
            if (antwoordEnActieParen != null && antwoordEnActieParen.containsKey(answer)) {
                vragenMap.get(antwoordEnActieParen.get(answer)).step();
            }
        } catch (AfleidingsregelException ex) {
            addMissendeGegegevens(ex.getMissendVeld());
            arAntwoordenModel.setException(ex);
        } catch (Exception ex) {
            errorTraceCode = UUID.randomUUID();
            logger.error("Programmeerfout tijdens het bepalen van gezag ({})", errorTraceCode, ex);

            arAntwoordenModel.setRoute("0");
            arAntwoordenModel.setException(ex);
        }
    }

    /**
     * @return burgerservicenummer van ouder 1 of null wanneer ouder 1 geen waarde heeft
     */
    public String getBurgerservicenummerOuder1() {
        return plOuder1 != null && plOuder1.getPersoon() != null ? plOuder1.getPersoon().getBurgerservicenummer() : null;
    }

    /**
     * @return burgerservicenummer van ouder 2 of null wanneer ouder 2 geen waarde heeft
     */
    public String getBurgerservicenummerOuder2() {
        return plOuder2 != null && plOuder2.getPersoon() != null ? plOuder2.getPersoon().getBurgerservicenummer() : null;
    }

    /**
     * @return burgerservicenummer van niet ouder of null wanneer niet ouder geen waarde heeft
     */
    public String getBurgerservicenummerNietOuder() {
        return plNietOuder != null && plNietOuder.getPersoon() != null ? plNietOuder.getPersoon().getBurgerservicenummer() : null;
    }

    /**
     * @return of er velden in onderzoek waren (010120 en 080910 worden
     * gefiltered)
     */
    public boolean warenVeldenInOnderzoek() {
        List<String> veldenInOnderzoek = plPersoon.getUsedVeldenInOnderzoek();

        if (plOuder1 != null) veldenInOnderzoek.addAll(plOuder1.getUsedVeldenInOnderzoek());
        if (plOuder2 != null) veldenInOnderzoek.addAll(plOuder2.getUsedVeldenInOnderzoek());
        if (plNietOuder != null) veldenInOnderzoek.addAll(plNietOuder.getUsedVeldenInOnderzoek());

        if (!veldenInOnderzoek.isEmpty()) {
            logger.info("De volgende velden zijn in onderzoek: {}", veldenInOnderzoek);
        }

        return veldenInOnderzoek.stream()
            .anyMatch(veldInOnderzoek ->
                TE_NEGEREN_VELDEN_IN_ONDERZOEK.stream().noneMatch(veldInOnderzoek::contains)
            );
    }

    /**
     * @return velden in onderzoek per persoon
     */
    public VeldenInOnderzoek getVeldenInOnderzoek() {
        VeldenInOnderzoek velden = new VeldenInOnderzoek();
        velden.setPersoon(plPersoon.getUsedVeldenInOnderzoek());
        if (plOuder1 != null) {
            velden.setOuder1(plOuder1.getUsedVeldenInOnderzoek());
        }
        if (plOuder2 != null) {
            velden.setOuder2(plOuder2.getUsedVeldenInOnderzoek());
        }
        if (plNietOuder != null) {
            velden.setNietOuder(plNietOuder.getUsedVeldenInOnderzoek());
        }

        return velden;
    }

    public void addMissendeGegegevens(final String missendGegegeven) {
        if (missendeGegegevens == null) {
            missendeGegegevens = new ArrayList<>();
        }

        missendeGegegevens.add(missendGegegeven);
    }


    /*
     * Als een persoonslijst lokaal aanwezig is, wordt deze opgehaald.
     * Anders wordt deze uit het BRP opgehaald en lokaal opgeslagen.
     * De lokale kopie wordt gebruikt om de aanvullende businesslogica te voorzien van persoonsgegevens
     */

    /**
     * @return ouder 1 of null
     */
    public Persoonslijst getPlOuder1() {
        if (plOuder1 == null) {
            try {
                if (plPersoon.getOuder1() != null && plPersoon.getOuder1().getBurgerservicenummer() != null) {
                    brpService.getPersoonslijst(
                            plPersoon.getOuder1().getBurgerservicenummer())
                        .ifPresent(ouder1 -> {
                            ouder1.setHopRelaties(new HopRelaties());
                            ouder1.checkHopRelaties();

                            plOuder1 = ouder1;
                        });
                }
            } catch (GezagException ex) {
                logger.debug(ex.getMessage());
            }
        }

        return plOuder1;
    }

    /**
     * @return ouder2 of null
     */
    public Persoonslijst getPlOuder2() {
        if (plOuder2 == null) {
            try {
                if (plPersoon.getOuder2() != null && plPersoon.getOuder2().getBurgerservicenummer() != null) {
                    brpService.getPersoonslijst(
                            plPersoon.getOuder2().getBurgerservicenummer())
                        .ifPresent(ouder2 -> {
                            ouder2.setHopRelaties(new HopRelaties());
                            ouder2.checkHopRelaties();
                            plOuder2 = ouder2;
                        });
                }
            } catch (GezagException ex) {
                logger.debug(ex.getMessage());
            }
        }

        return plOuder2;
    }

    /**
     * @return de niet ouder of null
     */
    public Persoonslijst getPlNietOuder() {
        if (plNietOuder == null) {
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

                String burgerservicenummerNietOuder = hopGeborenInRelatie.getPartner();
                brpService.getPersoonslijst(burgerservicenummerNietOuder)
                    .ifPresent(nietOuder ->
                        plNietOuder = nietOuder);
            } catch (GezagException ex) {
                logger.debug(ex.getMessage());
                return null;
            }
        }

        return plNietOuder;
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

    private void initializeVragenMap() {
        vragenMap.put("v1.1", new IsPersoonIngezeteneInBRP(this));
        vragenMap.put("v1.2", new IsPersoonMinderjarigEnNietOverleden(this));
        vragenMap.put("v1.3", new IsNaarBuitenlandGeemigreerdGeweest(this));
        vragenMap.put("v1.3a", new IsGeborenInBuitenland(this));
        vragenMap.put("v1.3b", new IsGeadopteerdMetNlAkte(this));
        vragenMap.put("v1.4", new IsUitspraakGezagAanwezig(this));
        vragenMap.put("v2.1", new HoeveelJuridischeOudersHeeftMinderjarige(this));
        vragenMap.put("v2a.1", new ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners(this));
        vragenMap.put("v2a.2", new AdoptiefOuders(this));
        vragenMap.put("v2a.3", new ErkenningNa01012023(this));
        vragenMap.put("v2b.1", new IsStaandeHuwelijkOfPartnerschapGeboren(this));
        vragenMap.put("v3.1", new IsErSprakeVanEenRecenteGebeurtenis(this));
        vragenMap.put("v3.2", new IndicatieGezagMinderjarige(this));
        vragenMap.put("v4a.2", new OudersOverledenOfOnbevoegdTotGezag(this));
        vragenMap.put("v4a.3", new OuderOverledenOfOnbevoegdTotGezag(this));
        vragenMap.put("v4b.1", new OuderOfPartnerOverledenOfOnbevoegdTotGezag(this));
    }
}
