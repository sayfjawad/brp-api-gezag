package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;

import java.util.*;

@Slf4j
public class GezagBepaling {

    @Getter
    private final Persoonslijst plPersoon;
    private Persoonslijst plOuder1;
    private Persoonslijst plOuder2;
    private Persoonslijst plNietOuder;
    private final GezagService gezagService;
    private final Map<String, Map<String, String>> hoofdstroomschema;
    private Map<String, GezagVraag> vragenMap;
    @Getter
    private final Transaction transaction;
    @Getter
    private final ARAntwoordenModel arAntwoordenModel;
    @Getter
    private final List<String> missendeGegegevens;

    public GezagBepaling(
        final Persoonslijst plPersoon,
        final GezagService gezagService,
        final Map<String, Map<String, String>> hoofdstroomschema,
        final Transaction transaction) {
        this.plPersoon = plPersoon;
        this.gezagService = gezagService;
        this.hoofdstroomschema = hoofdstroomschema;
        this.transaction = transaction;

        arAntwoordenModel = new ARAntwoordenModel();
        vragenMap = new HashMap<>();
        missendeGegegevens = new ArrayList<>();

        initializeVragenMap();
    }

    /**
     * Start de gezag bepaling
     */
    public void start() {
        vragenMap.get(0).step();
    }

    public void next(final String currentQuestion, final String answer) {
        try {
            Map<String, String> antwoordEnActieParen = hoofdstroomschema.get(currentQuestion);
            if (antwoordEnActieParen != null && antwoordEnActieParen.containsKey(answer)) {
                vragenMap.get(antwoordEnActieParen.get(answer)).step();
            }
        } catch (Exception ex) {
            arAntwoordenModel.setException(ex);
        }
    }

    public Persoonslijst getPlOuder1() {
        if (plOuder1 == null && gezagService != null) {
            plOuder1 = gezagService.ophalenOuder1(plPersoon, transaction);
        }

        return plOuder1;
    }

    public Persoonslijst getPlOuder2() {
        if (plOuder2 == null && gezagService != null) {
            plOuder2 = gezagService.ophalenOuder2(plPersoon, transaction);
        }

        return plOuder2;
    }

    public Persoonslijst getPlNietOuder() {
        if (plNietOuder == null && gezagService != null) {
            plNietOuder = gezagService.ophalenNietOuder(plPersoon, plOuder1, plOuder2, transaction);
        }

        return plNietOuder;
    }

    /**
     * @return of er velden in onderzoek waren (010120 en 080910 worden
     * gefiltered)
     */
    public boolean warenVeldenInOnderzoek() {
        List<String> veldenInOnderzoek = plPersoon.getUsedVeldenInOnderzoek();
        if (plOuder1 != null) {
            veldenInOnderzoek.addAll(plOuder1.getUsedVeldenInOnderzoek());
        }

        if (plOuder2 != null) {
            veldenInOnderzoek.addAll(plOuder2.getUsedVeldenInOnderzoek());
        }

        if (plNietOuder != null) {
            veldenInOnderzoek.addAll(plNietOuder.getUsedVeldenInOnderzoek());
        }

        if (!veldenInOnderzoek.isEmpty()) {
            log.info("Velden waren in onderzoek: {}", veldenInOnderzoek);
        }

        veldenInOnderzoek = filterVelden(veldenInOnderzoek);

        return !veldenInOnderzoek.isEmpty();
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

    /*
     * Probeer hier niet de ouders of nietouders op te halen, in dit stadium zijn
     * deze al lang opgehaald of was dit onnodig.
     */
    public Set<String> bepalenGezagdragers(final ARAntwoordenModel arAntwoordenModel) {
        Set<String> gezagsdragers = new HashSet<>();
        if (arAntwoordenModel != null) {
            if (arAntwoordenModel.getGezagOuder1() != null && arAntwoordenModel.getGezagOuder1().equals("Ja")
                && (plOuder1 != null)) {
                gezagsdragers.add(plOuder1.getPersoon().getBsn());
            }
            if (arAntwoordenModel.getGezagOuder2() != null && arAntwoordenModel.getGezagOuder2().equals("Ja")
                && (plOuder2 != null)) {
                gezagsdragers.add(plOuder2.getPersoon().getBsn());
            }
            if ((((arAntwoordenModel.getGezagNietOuder1() != null && arAntwoordenModel.getGezagNietOuder1().equals("Ja"))
                || (arAntwoordenModel.getGezagNietOuder2() != null && arAntwoordenModel.getGezagNietOuder2().equals("Ja")))
                && (plNietOuder != null))) {
                gezagsdragers.add(plNietOuder.getPersoon().getBsn());
            }
        }

        return gezagsdragers;
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

    /**
     * Verwijder velden in onderzoek die we niet willen overwegen
     *
     * @param veldenInOnderzoek lijst met velden in onderzoek om te filteren
     */
    private List<String> filterVelden(final List<String> veldenInOnderzoek) {
        return veldenInOnderzoek.stream()
            .filter(v -> !v.equals("bsn") && !v.equals("gemeente van inschrijving"))
            .toList();
    }
}
