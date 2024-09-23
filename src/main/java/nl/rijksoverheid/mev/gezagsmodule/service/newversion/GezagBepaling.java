package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import lombok.Getter;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GezagBepaling {

    private static final Logger logger = LoggerFactory.getLogger(GezagBepaling.class);

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

        if (plOuder1 != null) veldenInOnderzoek.addAll(plOuder1.getUsedVeldenInOnderzoek());
        if (plOuder2 != null) veldenInOnderzoek.addAll(plOuder2.getUsedVeldenInOnderzoek());
        if (plNietOuder != null) veldenInOnderzoek.addAll(plNietOuder.getUsedVeldenInOnderzoek());

        logger.info("De volgende velden zijn in onderzoek: {}", veldenInOnderzoek);

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
    public void bepalenGezagdragers(final String burgerservicenummer, final String burgerservicenummerPersoon, final ARAntwoordenModel arAntwoordenModel, final List<Gezagsrelatie> gezagsrelaties) {
        if (arAntwoordenModel != null){
            //tenminsteEenRelatieMetPersoon(burgerservicenummerPersoon)) {
            String uitleg = arAntwoordenModel.getUitleg();
            String soortGezag = arAntwoordenModel.getSoortGezag();

            boolean ouder1Gezag = arAntwoordenModel.getGezagOuder1() != null && arAntwoordenModel.getGezagOuder1().equals("Ja")
                && (plOuder1 != null);
            boolean ouder2Gezag = arAntwoordenModel.getGezagOuder2() != null && arAntwoordenModel.getGezagOuder2().equals("Ja")
                && (plOuder2 != null);
            boolean nietOuderGezag = (((arAntwoordenModel.getGezagNietOuder1() != null && arAntwoordenModel.getGezagNietOuder1().equals("Ja"))
                || (arAntwoordenModel.getGezagNietOuder2() != null && arAntwoordenModel.getGezagNietOuder2().equals("Ja")))
                && (plNietOuder != null));

            System.out.println(ouder1Gezag);
            System.out.println(ouder2Gezag);
            System.out.println(nietOuderGezag);
            if (ouder1Gezag) {
                Gezagsrelatie gezagsrelatie = new Gezagsrelatie(burgerservicenummer, soortGezag, plOuder1.getPersoon().getBsn(), uitleg);
                if (nietOuderGezag) {
                    gezagsrelatie.setBsnDerde(plNietOuder.getPersoon().getBsn());
                }
                gezagsrelaties.add(gezagsrelatie);
            }
            if (ouder2Gezag) {
                Gezagsrelatie gezagsrelatie = new Gezagsrelatie(burgerservicenummer, soortGezag, plOuder2.getPersoon().getBsn(), uitleg);
                if (nietOuderGezag) {
                    gezagsrelatie.setBsnDerde(plNietOuder.getPersoon().getBsn());
                }
                gezagsrelaties.add(gezagsrelatie);
            }
            if (!ouder1Gezag && !ouder2Gezag && nietOuderGezag) {
                Gezagsrelatie gezagsrelatie = new Gezagsrelatie(burgerservicenummer, soortGezag, null, uitleg);
                gezagsrelatie.setBsnDerde(plNietOuder.getPersoon().getBsn());
                gezagsrelaties.add(gezagsrelatie);
            }
        }
    }

    private boolean tenminsteEenRelatieMetPersoon(final String burgerservicenummerPersoon) {
        return (plOuder1 != null && plOuder1.getPersoon() != null && plOuder1.getPersoon().getBsn().equals(burgerservicenummerPersoon)) ||
            (plOuder2 != null && plOuder2.getPersoon() != null && plOuder2.getPersoon().getBsn().equals(burgerservicenummerPersoon)) ||
            (plNietOuder != null && plNietOuder.getPersoon() != null && plNietOuder.getPersoon().getBsn().equals(burgerservicenummerPersoon));
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
