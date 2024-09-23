package nl.rijksoverheid.mev.gezagsmodule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlVerblijfplaatsRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PersoonslijstVeld;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.BrpException;

import java.lang.reflect.Field;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * De Persoonslijst is onderverdeeld in een aantal categorieën met bij elkaar
 * horende gegevens. Zie: <a
 * href="<a">href="https://www.rvig.nl/brp/documenten/publicaties/2019/06/26/logisch-ontwerp-gba-ve</a>rsie-3.12">...</a>
 * (p. 151 en verder)
 */
@Setter
public class Persoonslijst {

    private Map<String, PersoonslijstVeld> values;
    private Map<String, List<PersoonslijstVeld>> listValues;

    private final Clock clock;

    public Persoonslijst(final Clock clock) {
        values = new HashMap<>();
        listValues = new HashMap<>();

        this.clock = clock;
    }

    public void addGezagsverhouding(final Lo3PlGezagsverhoudingRecord lo3PlGezagsverhoudingRecord) {
        addVeld(Categorie.GEZAGSVERHOUDING, Gezagsverhouding.from(lo3PlGezagsverhoudingRecord, clock));
    }

    public void addVerblijfplaats(final Lo3PlVerblijfplaatsRecord lo3PlVerblijfplaatsRecord) {
        addVeld(Categorie.VERBLIJFPLAATS, Verblijfplaats.from(lo3PlVerblijfplaatsRecord, clock));
    }

    public void addRelatie(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeldToList(Categorie.HUWELIJK_OF_PARTNERSCHAP, HuwelijkOfPartnerschap.from(lo3PlPersoonRecord, clock));
    }

    public void addKind(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeldToList(Categorie.KIND, Kind.from(lo3PlPersoonRecord, clock));
    }

    public void addOuder1(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeld(Categorie.OUDER_1, Ouder1.from(lo3PlPersoonRecord, clock));
    }

    public void addOuder1Geschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeldToList(Categorie.GESCHIEDENIS_OUDER_1, GeschiedenisOuder1.from(lo3PlPersoonRecord, clock));
    }

    public void addOuder2(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeld(Categorie.OUDER_2, Ouder2.from(lo3PlPersoonRecord, clock));
    }

    public void addOuder2Geschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeldToList(Categorie.GESCHIEDENIS_OUDER_2, GeschiedenisOuder2.from(lo3PlPersoonRecord, clock));
    }

    public void addPersoon(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeld(Categorie.PERSOON, Persoon.from(lo3PlPersoonRecord, clock));
    }

    public void addPersoonGeschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        addVeldToList(Categorie.GESCHIEDENIS_PERSOON, GeschiedenisPersoon.from(lo3PlPersoonRecord, clock));
    }

    public void addInschrijving(final Lo3PlRecord lo3PlRecord) {
        addVeld(Categorie.INSCHRIJVING, Inschrijving.from(lo3PlRecord));
    }

    public <T extends PersoonslijstVeld> void addVeld(final String categorie, final T veld) {
        values.put(categorie, veld);
    }

    public <T extends PersoonslijstVeld> void addVeldToList(final String categorie, final T veld) {
        listValues.merge(
                categorie,
                List.of(veld),
                (list1, list2) -> Stream.of(list1, list2)
                        .flatMap(Collection::stream).toList());
    }

    public List<String> getUsedVeldenInOnderzoek() {
        List<String> inOnderzoek = new ArrayList<>();
        for (PersoonslijstVeld value : values.values()) {
            // list values
            if (value instanceof PotentieelInOnderzoek potentieelInOnderzoek) {
                inOnderzoek.addAll(potentieelInOnderzoek.getVeldenInOnderzoek());
            }
        }

        return inOnderzoek;
    }

    public Persoon getPersoon() {
        if (values.containsKey(Categorie.PERSOON)) {
            return (Persoon) values.get(Categorie.PERSOON);
        } else {
            return null;
        }
    }

    public Ouder1 getOuder1() {
        if (values.containsKey(Categorie.OUDER_1)) {
            return (Ouder1) values.get(Categorie.OUDER_1);
        } else {
            return null;
        }
    }

    public Ouder2 getOuder2() {
        if (values.containsKey(Categorie.OUDER_2)) {
            return (Ouder2) values.get(Categorie.OUDER_2);
        } else {
            return null;
        }
    }

    public List<HuwelijkOfPartnerschap> getHuwelijkOfPartnerschappen() {
        if (listValues.containsKey(Categorie.HUWELIJK_OF_PARTNERSCHAP)) {
            return (List<HuwelijkOfPartnerschap>) (Object) listValues.get(Categorie.HUWELIJK_OF_PARTNERSCHAP);
        } else {
            return new ArrayList<>();
        }
    }

    public Inschrijving getInschrijving() {
        if (values.containsKey(Categorie.INSCHRIJVING)) {
            return (Inschrijving) values.get(Categorie.INSCHRIJVING);
        } else {
            return null;
        }
    }

    public Verblijfplaats getVerblijfplaats() {
        if (values.containsKey(Categorie.VERBLIJFPLAATS)) {
            return (Verblijfplaats) values.get(Categorie.VERBLIJFPLAATS);
        } else {
            return null;
        }
    }

    public List<Kind> getKinderen() {
        if (listValues.containsKey(Categorie.KIND)) {
            return (List<Kind>) (Object) listValues.get(Categorie.KIND);
        } else {
            return new ArrayList<>();
        }
    }

    public Gezagsverhouding getGezagsverhouding() {
        if (values.containsKey(Categorie.GEZAGSVERHOUDING)) {
            return (Gezagsverhouding) values.get(Categorie.GEZAGSVERHOUDING);
        } else {
            return null;
        }
    }

    public List<GeschiedenisHuwelijkOfPartnerschap> getGeschiedenisHuwelijkOfPartnerschappen() {
        if (listValues.containsKey(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP)) {
            return (List<GeschiedenisHuwelijkOfPartnerschap>) (Object) listValues.get(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP);
        } else {
            return new ArrayList<>();
        }
    }

    public List<GeschiedenisPersoon> getGeschiedenisPersoon() {
        if (listValues.containsKey(Categorie.GESCHIEDENIS_PERSOON)) {
            return (List<GeschiedenisPersoon>) (Object) listValues.get(Categorie.GESCHIEDENIS_PERSOON);
        } else {
            return new ArrayList<>();
        }
    }

    public List<GeschiedenisOuder1> getGeschiedenisOuder1() {
        if (listValues.containsKey(Categorie.GESCHIEDENIS_OUDER_1)) {
            return (List<GeschiedenisOuder1>) (Object) listValues.get(Categorie.GESCHIEDENIS_OUDER_1);
        } else {
            return new ArrayList<>();
        }
    }

    public List<GeschiedenisOuder2> getGeschiedenisOuder2() {
        if (listValues.containsKey(Categorie.GESCHIEDENIS_OUDER_2)) {
            return (List<GeschiedenisOuder2>) (Object) listValues.get(Categorie.GESCHIEDENIS_OUDER_2);
        } else {
            return new ArrayList<>();
        }
    }
    public static final int MEERDERJARIGE_LEEFTIJD = 180000;
    public static final int AKTE_NUMMER_LENGTE = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    /*
        In het BRP wordt een puntouder vastgelegd in de geslachtsnaam met een "."
     */
    public static final String GEEN_OUDERS = "Geen_ouders";
    public static final String EEN_OUDER = "Een_ouder";
    public static final String TWEE_OUDERS = "Twee_ouders";
    public static final String GESLACHTNAAM_AANDUIDING_PUNTOUDER = ".";

    /*
        Mogelijke antwoorden op de vraag: Hoeveel Juridische Ouders
     */
    public static final String PUNTOUDERS = "1_of_2_puntouders";
    /*
        Akte aanduidingen die gebruikt worden om adoptie of erkenning vast te stellen
        Vaderschap is toegevoegd om in situaties van 1 ouderlijk gezag de gezaghebbende ouder te kunnen bepalen
     */
    public static final char TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE = 'B';
    public static final char TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP = 'E'; // Rechterlijke uitspraak
    public static final char TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE = 'C';
    public static final char TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING = 'J';
    public static final char TABEL_39_AKTEAANDUIDING_ADOPTIE = 'Q';
    public static final char TABEL_39_AKTEAANDUIDING_GEBOORTE = 'A';
    public static final char TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP = 'V';
    public static final String PUNTOUDER_INDICATIE = ".";
    static final List<String> INDICATIE_GEZAG_CODES = Arrays.asList("1", "2", "12", "1D", "2D", "D");
    static final String[] HUWELIJK_OF_PARTNERSCHAP_REDEN_ONTBINDING = new String[]{"O", "R"};

    @JsonIgnore
    @Getter
    private HopRelaties hopRelaties = null;

    @Getter
    private String receivedId;

    public List<String> getBurgerservicenummersVanKinderen() {
        if (getKinderen() == null) {
            return Collections.emptyList();
        }

        return getKinderen().stream()
                .map(Kind::getBsn)
                .filter(Objects::nonNull)
                .toList();
    }

    public List<String> getBurgerservicenummersVanMinderjarigeKinderen() {
        if (getKinderen() == null) {
            return Collections.emptyList();
        }

        return getKinderen().stream()
                .filter(Kind::isMinderjarig)
                .map(Kind::getBsn)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Controleer huwelijk of partnerschap relaties
     * <p>
     * In het BRP worden drie manieren van registreren gebruikt
     *
     * <li>Bij overlijden van de partner wordt bij de actuele hop de ontbindingsdatum bijgeschreven</li>
     * <li>Bij eigen overlijden wordt niets veranderd en lijkt het alsof de hop nog bestaat</li>
     * <li>Bij scheiding wordt alleen de scheiding in de actuele hop geregistreerd en
     * de voltrekking van de hop gaat naar de geschiedenis.</li>
     *
     * Voor het maken van een gebeurtenissen tijdlijn wordt voltrekking en ontbinding geregistreerd
     * als twee gebeurtenissen, daarom kan het gebeuren dat per hop twee gebeurtenissen worden geregistreerd
     * dit is het geval als de reden van ontbinding het overlijden van de partner betreft.
     */
    public void checkHopRelaties() {
        for (HuwelijkOfPartnerschap gebeurtenis : getHuwelijkOfPartnerschappen()) {
            if (gebeurtenis.getDatumOntbinding() != null) {
                hopRelaties.voegGebeurtenisToe(HopRelaties.ONTBINDING_RELATIE,
                        gebeurtenis.getBsnPartner(),
                        gebeurtenis.getDatumOntbinding(),
                        gebeurtenis.getRedenOntbinding()
                );
            }
            if (gebeurtenis.getDatumVoltrokken() != null) {
                hopRelaties.voegGebeurtenisToe(HopRelaties.START_RELATIE,
                        gebeurtenis.getBsnPartner(),
                        gebeurtenis.getDatumVoltrokken(),
                        null);
            }
        }
        if (getGeschiedenisHuwelijkOfPartnerschappen() != null) {
            for (GeschiedenisHuwelijkOfPartnerschap gebeurtenis : getGeschiedenisHuwelijkOfPartnerschappen()) {
                if (gebeurtenis.getDatumVoltrokken() != null) {
                    hopRelaties.voegGebeurtenisToe(HopRelaties.START_RELATIE,
                            gebeurtenis.getBsnPartner(),
                            gebeurtenis.getDatumVoltrokken(),
                            gebeurtenis.getRedenOntbinding()
                    );
                }
                if (gebeurtenis.getDatumOntbinding() != null) {
                    hopRelaties.voegGebeurtenisToe(HopRelaties.ONTBINDING_RELATIE,
                            gebeurtenis.getBsnPartner(),
                            gebeurtenis.getDatumOntbinding(),
                            gebeurtenis.getRedenOntbinding()
                    );
                }
            }
        }
        hopRelaties.checkRelaties();
    }

    @JsonIgnore
    public boolean isOpgeschort() {
        // PL 1/2 : 07.67.10
        Inschrijving inschrijving = getInschrijving();
        return (inschrijving != null
                && inschrijving.getDatumOpschortingBijhouding() != null);
    }

    @JsonIgnore
    public boolean isOverleden() {
        // PL 1/2 : 07.67.10
        Inschrijving inschrijving = getInschrijving();
        return (inschrijving != null
            && inschrijving.getDatumOpschortingBijhouding() != null)
            && inschrijving.getRedenOpschortingBijhouding().equals("O");
    }

    @JsonIgnore
    public boolean isNietIngeschrevenInRNI() {
        // PL 1/2 : 07.67.20
        Inschrijving inschrijving = getInschrijving();
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "R"));
    }

    @JsonIgnore
    public boolean isNietGeemigreerd() {
        // PL 1/2 : 07.67.20
        Inschrijving inschrijving = getInschrijving();
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "E"));
    }

    public boolean onderCurateleGesteld() {
        Gezagsverhouding gezagsverhouding = getGezagsverhouding();
        return gezagsverhouding != null
                && isNotBlank(gezagsverhouding.getIndicatieCurateleRegister());
    }

    @JsonIgnore
    public boolean isOverledenOfOnbevoegd() throws AfleidingsregelException {
        return isOpgeschort() || minderjarig() || onderCurateleGesteld();
    }

    public boolean alsMinderjarigeOpgeschort() throws AfleidingsregelException {
        if (isOpgeschort()){
            int datumOpschorting = Integer.parseInt(getInschrijving().getDatumOpschortingBijhouding());
            int meerderjarigheidDatum = Integer.parseInt(getPersoon().getGeboortedatum())
                    + MEERDERJARIGE_LEEFTIJD;
            return datumOpschorting < meerderjarigheidDatum;
        }
        return false;
    }

    public boolean heeftTweeOuders() {
        if ((getOuder1() != null) && (getOuder2() != null)) {
            return isValideGeslachtsnaam(getOuder1().getGeslachtsnaam()) && isValideGeslachtsnaam(getOuder2().getGeslachtsnaam());
        }
        return false;
    }

    public static boolean isValideGeslachtsnaam(String str) {
        return str != null && !str.isEmpty() && !str.isBlank() && !str.equals(PUNTOUDER_INDICATIE) ;
    }

    /**
     * Controleert een lijst van aktenummers op geldige erkenningscodes.
     *
     * @param akteNummers Een lijst van String objecten die de te controleren aktenummers bevat.
     *                    Elk aktenummer moet minimaal {@value #AKTE_NUMMER_LENGTE} tekens lang zijn.
     * @param geldigeErkenningCodes Een Set van Character objecten die de geldige erkenningscodes bevat.
     *                                Deze codes worden vergeleken met het derde teken van elk aktenummer.
     * @return {@code true} als er minstens één aktenummer is gevonden met een geldige erkenningscode,
     *         {@code false} als er geen enkel aktenummer met een geldige erkenningscode is gevonden.
     * @throws NullPointerException als {@code akteNummers} of {@code geldigeErkenningCodes} null is.
     */
    public static boolean controleerAkteNummers(List<String> akteNummers, Set<Character> geldigeErkenningCodes) {
        return akteNummers.stream()
            .filter(aktenummer -> aktenummer != null && aktenummer.length() >= AKTE_NUMMER_LENGTE)
            .anyMatch(aktenummer -> geldigeErkenningCodes.contains(aktenummer.charAt(2)));
    }

    public boolean geenOngeborenVruchtErkendOfGerechtelijkeVaststelling() {
        // controleer dan persoon op akte erkenning actueel en geschiedenis op B, C, J en V
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getPersoon().getAktenummer());
        List<GeschiedenisPersoon> geschiedenisPersoon = getGeschiedenisPersoon();
        if (geschiedenisPersoon != null) {
            for (GeschiedenisPersoon p : geschiedenisPersoon) {
                akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(Arrays.asList(
            TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE,
            TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE,
            TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING,
            TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }



    public boolean geenOngeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling() {
        // controleer dan op akte erkenning actueel en geschiedenis op B, C, J en V
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getOuder1().getAktenummer());
        List<GeschiedenisOuder1> geschiedenisOuder1 = getGeschiedenisOuder1();
        if (geschiedenisOuder1 != null) {
            for (GeschiedenisOuder1 p : geschiedenisOuder1) {
                    akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(Arrays.asList(
            TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE,
            TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE,
            TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING,
            TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }

    public boolean ontkenningOuderschapDoorOuder1() {
        // controleer dan op akte erkenning actueel en geschiedenis op E
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getOuder1().getAktenummer());
        List<GeschiedenisOuder1> geschiedenisOuder1 = getGeschiedenisOuder1();
        if (geschiedenisOuder1 != null) {
            for (GeschiedenisOuder1 p : geschiedenisOuder1) {
                akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(List.of(
            TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }

    public boolean ontkenningOuderschapDoorOuder2() {
        // controleer dan op akte erkenning actueel en geschiedenis op E
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getOuder2().getAktenummer());
        List<GeschiedenisOuder2> geschiedenisOuder2 = getGeschiedenisOuder2();
        if (geschiedenisOuder2 != null) {
            for (GeschiedenisOuder2 p : geschiedenisOuder2) {
                akteNummers.add(p.getAktenummer());
            }
        }
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        Set<Character> geldigeErkenningCodes = new HashSet<>(List.of(
            TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }

    public boolean ongeborenVruchtErkend() {
        // controleer dan persoon op akte erkenning actueel en geschiedenis op A
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getPersoon().getAktenummer());
        List<GeschiedenisPersoon> geschiedenisPersoon = getGeschiedenisPersoon();
        if (geschiedenisPersoon != null) {
            for (GeschiedenisPersoon p : geschiedenisPersoon) {
                akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(List.of(
            TABEL_39_AKTEAANDUIDING_GEBOORTE));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }


    public boolean geenOngeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling() {
        // controleer dan op akte erkenning actueel en geschiedenis op B, C, J en V
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getOuder2().getAktenummer());
        List<GeschiedenisOuder2> geschiedenisOuder2 = getGeschiedenisOuder2();
        if (geschiedenisOuder2 != null) {
            for (GeschiedenisOuder2 p : geschiedenisOuder2) {
                akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(Arrays.asList(
            TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE,
            TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE,
            TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING,
            TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }

    public boolean beideOudersHebbenEenBSN() {
        String bsnOuder1 = getOuder1().getBsn();
        String bsnOuder2 = getOuder2().getBsn();
        if (bsnOuder1 != null && bsnOuder2 != null) {
            return !bsnOuder1.isEmpty() && !bsnOuder2.isEmpty();
        } else {
            return false;
        }
    }

    @JsonIgnore
    public boolean isIngezeteneInBRP() {
        Verblijfplaats verblijfplaats = getVerblijfplaats();
        if (verblijfplaats != null && verblijfplaats.getGemeenteVanInschrijving() != null) {
            return !verblijfplaats.getGemeenteVanInschrijving().equals("1999");
        }
        return false;
    }

    public String hoeveelJuridischeOuders() {
        String geslachtsnaamOuder1 = getOuder1() == null ? null : getOuder1().getGeslachtsnaam();
        String geslachtsnaamOuder2 = getOuder2() == null ? null : getOuder2().getGeslachtsnaam();
        if (Objects.equals(geslachtsnaamOuder1, GESLACHTNAAM_AANDUIDING_PUNTOUDER)
                || Objects.equals(geslachtsnaamOuder2, GESLACHTNAAM_AANDUIDING_PUNTOUDER)) {
            return PUNTOUDERS;
        } else if (geslachtsnaamOuder1 == null && geslachtsnaamOuder2 == null) {
            return GEEN_OUDERS;
        } else if (geslachtsnaamOuder1 == null ^ geslachtsnaamOuder2 == null) {
            return EEN_OUDER;
        } else {
            return TWEE_OUDERS;
        }
    }

    public boolean adoptieNaIngangGeldigheidsdatum() {
        if (!geadopteerdMetNlAkte()) return false;

        String ingangsdatumGeldigheidGezag = getGezagsverhouding().getIngangsdatumGeldigheidGezag();
        if (ingangsdatumGeldigheidGezag == null) return false;

        String datumIngangFamiliebetrekkingOuder1 = getOuder1().getDatumIngangFamiliebetrekking();
        if (datumIngangFamiliebetrekkingOuder1 == null) return false;
        boolean ouder1AdoptieNa = Integer.parseInt(datumIngangFamiliebetrekkingOuder1) >= Integer.parseInt(ingangsdatumGeldigheidGezag);

        String datumIngangFamiliebetrekkingOuder2 = getOuder2().getDatumIngangFamiliebetrekking();
        if (datumIngangFamiliebetrekkingOuder2 == null) return false;
        boolean ouder2AdoptieNa = Integer.parseInt(datumIngangFamiliebetrekkingOuder2) >= Integer.parseInt(ingangsdatumGeldigheidGezag);

        return ouder1AdoptieNa || ouder2AdoptieNa;
    }

    public boolean geadopteerdMetNlAkte() {
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(getPersoon().getAktenummer());
        List<GeschiedenisPersoon> geschiedenisPersoon = getGeschiedenisPersoon();
        if (geschiedenisPersoon != null) {
            for (GeschiedenisPersoon p : geschiedenisPersoon) {
                akteNummers.add(p.getAktenummer());
            }
        }
        Set<Character> geldigeErkenningCodes = new HashSet<>(List.of(
            TABEL_39_AKTEAANDUIDING_ADOPTIE));
        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(akteNummers, geldigeErkenningCodes);
    }

    public boolean heefIndicatieGezag() {
        Gezagsverhouding gezagsverhouding = getGezagsverhouding();
        return gezagsverhouding != null
                && INDICATIE_GEZAG_CODES.contains(gezagsverhouding.getIndicatieGezagMinderjarige());
    }

    public boolean minderjarig() throws AfleidingsregelException {
        // PL 1/2 : 01.03.10 
        Persoon persoon = getPersoon();
        if (persoon == null) {
            throw new AfleidingsregelException("Preconditie: persoon mag niet leeg zijn", "persoon");
        } else if (persoon.getGeboortedatum() == null) {
            throw new AfleidingsregelException("Preconditie: geboortedatum mag niet leeg zijn", "geboortedatum");
        }

        int geboortedatum = Integer.parseInt(persoon.getGeboortedatum());
        int datumVolwassenVanaf = Integer.parseInt(LocalDate.now(clock).format(FORMATTER)) - MEERDERJARIGE_LEEFTIJD;
        return geboortedatum > datumVolwassenVanaf;
    }

    /**
     * @return of een van de velden een waarde heeft in de persoonslijst
     * @throws BrpException als een invalide veld benaderd wordt
     */
    public boolean hasAnyValue() throws BrpException {
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                if (f.getType() == List.class) {
                    List<?> x = (List) f.get(this);
                    if (x != null && !x.isEmpty()) {
                        return true;
                    }
                } else {
                    if (f.get(this) != null) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IllegalAccessException ex) {
            throw new BrpException(ex.getMessage());
        }
    }
}
