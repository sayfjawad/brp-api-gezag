package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import lombok.Getter;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagServiceOld;
import nl.rijksoverheid.mev.transaction.Transaction;

import java.util.HashMap;
import java.util.Map;

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
        IsPersoonIngezeteneInBRP startBepaling = new IsPersoonIngezeteneInBRP(this);
        vragenMap.put("v1.1", startBepaling);
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
        startBepaling.step();
    }

    public void next(final String currentQuestion, final String answer) {
        try {
            Map<String, String> antwoordEnActieParen = hoofdstroomschema.get(currentQuestion);
            if (antwoordEnActieParen != null && antwoordEnActieParen.containsKey(answer)) {
                vragenMap.get(antwoordEnActieParen.get(answer)).step();
            }
        } catch(Exception ex) {
            System.out.println("EEEEEE");
            System.out.println(ex.getMessage());
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
}
