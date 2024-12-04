package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import lombok.Getter;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import org.openapitools.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Gezag bepaling bevat alle informatie voor een gezag bepaling voor een persoon
 */
public class GezagBepaling {
    
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
    private final GezagService gezagService;
    private final Map<String, Map<String, String>> hoofdstroomschema;
    private Map<String, GezagVraag> vragenMap;
    @Getter
    private final ARAntwoordenModel arAntwoordenModel;

    private static final String TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG = "TweehoofdigOuderlijkGezag";
    private static final String TYPE_EENHOOFDIG_OUDERLIJK_GEZAG = "EenhoofdigOuderlijkGezag";
    private static final String TYPE_GEZAMELIJK_GEZAG = "GezamenlijkGezag";
    private static final String TYPE_VOOGDIJ = "Voogdij";
    private static final String TYPE_TIJDELIJK_GEEN_GEZAG = "TijdelijkGeenGezag";
    private static final String TYPE_GEZAG_NIET_TE_BEPALEN = "GezagNietTeBepalen";
    private static final Logger logger = LoggerFactory.getLogger(GezagBepaling.class);

    public GezagBepaling(
        final Persoonslijst plPersoon,
        final GezagService gezagService,
        final Map<String, Map<String, String>> hoofdstroomschema
    ) {
        this.plPersoon = plPersoon;
        this.gezagService = gezagService;
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

    public Persoonslijst getPlOuder1() {
        if (plOuder1 == null && gezagService != null) {
            plOuder1 = gezagService.ophalenOuder1(plPersoon);
        }

        return plOuder1;
    }

    public Persoonslijst getPlOuder2() {
        if (plOuder2 == null && gezagService != null) {
            plOuder2 = gezagService.ophalenOuder2(plPersoon);
        }

        return plOuder2;
    }

    public Persoonslijst getPlNietOuder() {
        if (plNietOuder == null && gezagService != null) {
            plNietOuder = gezagService.ophalenNietOuder(plPersoon, plOuder1, plOuder2);
        }

        return plNietOuder;
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

    public void bepalenGezagdragers(final String burgerservicenummer, final String burgerservicenummerPersoon, final ARAntwoordenModel arAntwoordenModel, final List<AbstractGezagsrelatie> gezagsrelaties) {
        boolean bevraagdePersoonIsDeMinderjarige = burgerservicenummer.equals(burgerservicenummerPersoon);
        if (arAntwoordenModel != null &&
            (bevraagdePersoonIsDeMinderjarige || tenminsteEenRelatieMetPersoon(burgerservicenummerPersoon))) {
            String soortGezag = arAntwoordenModel.getSoortGezag();

            boolean ouder1Gezag = arAntwoordenModel.getGezagOuder1() != null && arAntwoordenModel.getGezagOuder1().equals("Ja")
                && (plOuder1 != null);
            boolean ouder2Gezag = arAntwoordenModel.getGezagOuder2() != null && arAntwoordenModel.getGezagOuder2().equals("Ja")
                && (plOuder2 != null);
            boolean nietOuderGezag = (((arAntwoordenModel.getGezagNietOuder1() != null && arAntwoordenModel.getGezagNietOuder1().equals("Ja"))
                || (arAntwoordenModel.getGezagNietOuder2() != null && arAntwoordenModel.getGezagNietOuder2().equals("Ja")))
                && (plNietOuder != null));


            switch (soortGezag) {
                case "OG1" -> {
                    String burgerservicenummerOuder1 = (plOuder1 != null ? plOuder1.getPersoon().getBurgerservicenummer() : null);
                    String burgerservicenummerOuder2 = (plOuder2 != null ? plOuder2.getPersoon().getBurgerservicenummer() : null);
                    if (ouder1Gezag && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder1))) {
                        AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                            .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1))
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                        gezagsrelaties.add(gezag);
                    }

                    if (ouder2Gezag && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder2))) {
                        AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                            .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2))
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                        gezagsrelaties.add(gezag);
                    }
                }
                case "OG2" -> {
                    AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .addOudersItem(new GezagOuder().burgerservicenummer(plOuder1.getPersoon().getBurgerservicenummer()))
                        .addOudersItem(new GezagOuder().burgerservicenummer(plOuder2.getPersoon().getBurgerservicenummer()))
                        .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

                    gezagsrelaties.add(gezag);
                }
                case "GG" -> {
                    GezamenlijkGezag gezag = new GezamenlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .derde(new Meerderjarige().burgerservicenummer(plNietOuder.getPersoon().getBurgerservicenummer()))
                        .type(TYPE_GEZAMELIJK_GEZAG);

                    if (ouder1Gezag) {
                        gezag.ouder(new GezagOuder().burgerservicenummer(plOuder1.getPersoon().getBurgerservicenummer()));
                    } else {
                        gezag.ouder(new GezagOuder().burgerservicenummer(plOuder2.getPersoon().getBurgerservicenummer()));
                    }

                    gezagsrelaties.add(gezag);
                }
                case "V" -> {
                    String burgerservicenummerNietOuder = (plNietOuder != null ? plNietOuder.getPersoon().getBurgerservicenummer() : null);
                    if(bevraagdePersoonIsDeMinderjarige || burgerservicenummerNietOuder.equals(burgerservicenummerPersoon)) {
                        Voogdij gezag = new Voogdij()
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_VOOGDIJ);

                        if (nietOuderGezag && plNietOuder != null) {
                            gezag.addDerdenItem(new Meerderjarige().burgerservicenummer(burgerservicenummerNietOuder));
                        }

                        gezagsrelaties.add(gezag);
                    }
                }
                case String s when s.equals("G") && bevraagdePersoonIsDeMinderjarige -> gezagsrelaties.add(
                    new TijdelijkGeenGezag()
                        .type(TYPE_TIJDELIJK_GEEN_GEZAG)
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                );
                case String s when s.equals("N") && bevraagdePersoonIsDeMinderjarige -> gezagsrelaties.add(
                    new GezagNietTeBepalen()
                        .type(TYPE_GEZAG_NIET_TE_BEPALEN)
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .toelichting(arAntwoordenModel.getUitleg())
                );
                case "NVT" -> logger.info("Gezag uitkomst is NVT");
                default -> logger.warn("Onverwachte gezagsoort ontvangen: {}", soortGezag);
            }
        }
    }

    private boolean tenminsteEenRelatieMetPersoon(final String burgerservicenummerPersoon) {
        return (plOuder1 != null && plOuder1.getPersoon() != null && plOuder1.getPersoon().getBurgerservicenummer().equals(burgerservicenummerPersoon)) ||
            (plOuder2 != null && plOuder2.getPersoon() != null && plOuder2.getPersoon().getBurgerservicenummer().equals(burgerservicenummerPersoon)) ||
            (plNietOuder != null && plNietOuder.getPersoon() != null && plNietOuder.getPersoon().getBurgerservicenummer().equals(burgerservicenummerPersoon));
    }

    public void addMissendeGegegevens(final String missendGegegeven) {
        if (missendeGegegevens == null) {
            missendeGegegevens = new ArrayList<>();
        }

        missendeGegegevens.add(missendGegegeven);
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
