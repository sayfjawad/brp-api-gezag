package nl.rijksoverheid.mev.gezagsmodule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlVerblijfplaatsRecord;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
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

    private static final int MEERDERJARIGE_LEEFTIJD = 180000;
    private static final int AKTE_NUMMER_LENGTE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String GEEN_OUDERS = "Geen_ouders";
    private static final String EEN_OUDER = "Een_ouder";
    private static final String TWEE_OUDERS = "Twee_ouders";
    private static final String GESLACHTNAAM_AANDUIDING_PUNTOUDER = ".";
    private static final String PUNTOUDERS = "1_of_2_puntouders";
    private static final char TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE = 'B';
    private static final char TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP = 'E'; // Rechtelijke uitspraak
    private static final char TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE = 'C';
    private static final char TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING = 'J';
    private static final char TABEL_39_AKTEAANDUIDING_ADOPTIE = 'Q';
    private static final char TABEL_39_AKTEAANDUIDING_GEBOORTE = 'A';
    private static final char TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP = 'V';
    private static final String PUNTOUDER_INDICATIE = ".";
    private static final List<String> INDICATIE_GEZAG_CODES = Arrays.asList("1", "2", "12", "1D", "2D", "D");

    @Getter
    private Persoon persoon;
    private List<GeschiedenisPersoon> geschiedenisPersoon;
    @Getter
    private Gezagsverhouding gezagsverhouding;
    @Getter
    private Verblijfplaats verblijfplaats;
    @Getter
    private List<HuwelijkOfPartnerschap> huwelijkOfPartnerschappen;
    private List<Kind> kinderen;
    @Getter
    private Ouder1 ouder1;
    private List<GeschiedenisOuder1> geschiedenisOuder1;
    @Getter
    private Ouder2 ouder2;
    private List<GeschiedenisOuder2> geschiedenisOuder2;
    private Inschrijving inschrijving;
    @Getter
    private HopRelaties hopRelaties;

    public Persoonslijst() {
        huwelijkOfPartnerschappen = new ArrayList<>();
        kinderen = new ArrayList<>();
        geschiedenisOuder1 = new ArrayList<>();
        geschiedenisOuder2 = new ArrayList<>();
        geschiedenisPersoon = new ArrayList<>();
    }

    public void addGezagsverhouding(final Lo3PlGezagsverhoudingRecord lo3PlGezagsverhoudingRecord) {
        gezagsverhouding = new Gezagsverhouding(lo3PlGezagsverhoudingRecord);
    }

    public void addVerblijfplaats(final Lo3PlVerblijfplaatsRecord lo3PlVerblijfplaatsRecord) {
        verblijfplaats = new Verblijfplaats(lo3PlVerblijfplaatsRecord);
    }

    public void addRelatie(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        huwelijkOfPartnerschappen.add(new HuwelijkOfPartnerschap(lo3PlPersoonRecord));
    }

    public void addKind(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        kinderen.add(new Kind(lo3PlPersoonRecord));
    }

    public void addOuder1(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        ouder1 = new Ouder1(lo3PlPersoonRecord);
    }

    public void addOuder1Geschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        geschiedenisOuder1.add(new GeschiedenisOuder1(lo3PlPersoonRecord));
    }

    public void addOuder2(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        ouder2 = new Ouder2(lo3PlPersoonRecord);
    }

    public void addOuder2Geschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        geschiedenisOuder2.add(new GeschiedenisOuder2(lo3PlPersoonRecord));
    }

    public void addPersoon(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        persoon = new Persoon(lo3PlPersoonRecord);
    }

    public void addPersoonGeschiedenis(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        geschiedenisPersoon.add(new GeschiedenisPersoon(lo3PlPersoonRecord));
    }

    public void addInschrijving(final Lo3PlRecord lo3PlRecord) {
        inschrijving = new Inschrijving(lo3PlRecord);
    }

    public List<String> getUsedVeldenInOnderzoek() {
        return Stream.of(
                persoon.getVeldenInOnderzoek().stream(),
                geschiedenisPersoon.stream().map(GeschiedenisPersoon::getVeldenInOnderzoek).flatMap(Set::stream),
                Optional.ofNullable(gezagsverhouding).stream().map(Gezagsverhouding::getVeldenInOnderzoek).flatMap(Collection::stream),
                huwelijkOfPartnerschappen.stream().map(HuwelijkOfPartnerschap::getVeldenInOnderzoek).flatMap(Set::stream),
                ouder1.getVeldenInOnderzoek().stream(),
                geschiedenisOuder1.stream().map(GeschiedenisOuder1::getVeldenInOnderzoek).flatMap(Set::stream),
                ouder2.getVeldenInOnderzoek().stream(),
                geschiedenisOuder2.stream().map(GeschiedenisOuder2::getVeldenInOnderzoek).flatMap(Set::stream))
            .flatMap(Function.identity())
            .toList();
    }

    public Stream<String> getBurgerservicenummersVanMinderjarigeKinderen() {
        if (kinderen == null) return Stream.empty();

        return kinderen.stream()
            .filter(Kind::isMinderjarig)
            .map(Kind::getBurgerservicenummer)
            .filter(Objects::nonNull);
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
     * <p>
     * Voor het maken van een gebeurtenissen tijdlijn wordt voltrekking en ontbinding geregistreerd
     * als twee gebeurtenissen, daarom kan het gebeuren dat per hop twee gebeurtenissen worden geregistreerd
     * dit is het geval als de reden van ontbinding het overlijden van de partner betreft.
     */
    public void checkHopRelaties() {
        for (HuwelijkOfPartnerschap gebeurtenis : huwelijkOfPartnerschappen) {
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
        hopRelaties.checkRelaties();
    }

    @JsonIgnore
    public boolean isOpgeschort() {
        // PL 1/2 : 07.67.10
        return (inschrijving != null
            && inschrijving.getDatumOpschortingBijhouding() != null);
    }

    @JsonIgnore
    public boolean isOverleden() {
        // PL 1/2 : 07.67.10
        return (inschrijving != null
            && inschrijving.getDatumOpschortingBijhouding() != null)
            && inschrijving.getRedenOpschortingBijhouding().equals("O");
    }

    @JsonIgnore
    public boolean isNietIngeschrevenInRNI() {
        // PL 1/2 : 07.67.20
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "R"));
    }

    @JsonIgnore
    public boolean isNietGeemigreerd() {
        // PL 1/2 : 07.67.20
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "E"));
    }

    public boolean onderCurateleGesteld() {
        return gezagsverhouding != null
            && isNotBlank(gezagsverhouding.getIndicatieCurateleRegister());
    }

    @JsonIgnore
    public boolean isOverledenOfOnbevoegd() throws AfleidingsregelException {
        return isOpgeschort() || minderjarig() || onderCurateleGesteld();
    }

    public boolean alsMinderjarigeOpgeschort() throws AfleidingsregelException {
        if (isOpgeschort()) {
            int datumOpschorting = Integer.parseInt(inschrijving.getDatumOpschortingBijhouding());
            int meerderjarigheidDatum = Integer.parseInt(persoon.getGeboortedatum())
                + MEERDERJARIGE_LEEFTIJD;
            return datumOpschorting < meerderjarigheidDatum;
        }
        return false;
    }

    public boolean heeftTweeOuders() {
        if (ouder1 != null && ouder2 != null) {
            return isValideGeslachtsnaam(ouder1.getGeslachtsnaam()) && isValideGeslachtsnaam(ouder2.getGeslachtsnaam());
        }
        return false;
    }

    public static boolean isValideGeslachtsnaam(String str) {
        return str != null && !str.isBlank() && !str.equals(PUNTOUDER_INDICATIE);
    }

    /**
     * Controleert een lijst van aktenummers op geldige erkenningscodes.
     *
     * @param akteNummers           Een lijst van String objecten die de te controleren aktenummers bevat.
     *                              Elk aktenummer moet minimaal {@value #AKTE_NUMMER_LENGTE} tekens lang zijn.
     * @param geldigeErkenningCodes Een Set van Character objecten die de geldige erkenningscodes bevat.
     *                              Deze codes worden vergeleken met het derde teken van elk aktenummer.
     * @return {@code true} als er minstens één aktenummer is gevonden met een geldige erkenningscode,
     * {@code false} als er geen enkel aktenummer met een geldige erkenningscode is gevonden.
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
        akteNummers.add(persoon.getAktenummer());
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
        akteNummers.add(ouder1.getAktenummer());
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
        akteNummers.add(ouder1.getAktenummer());
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
        if (ouder2 != null) {
            akteNummers.add(ouder2.getAktenummer());
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
        var aktenummers = Stream.concat(
            Stream.of(persoon.getAktenummer()),
            geschiedenisPersoon.stream().map(GeschiedenisPersoon::getAktenummer)
        ).toList();
        var geldigeErkenningCodes = Set.of(TABEL_39_AKTEAANDUIDING_GEBOORTE);

        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(aktenummers, geldigeErkenningCodes);
    }


    public boolean geenOngeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling() {
        // controleer dan op akte erkenning actueel en geschiedenis op B, C, J en V
        // voorbereiding, zet alle aktenummers in een lijst
        List<String> akteNummers = new ArrayList<>();
        akteNummers.add(ouder2.getAktenummer());
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
        String bsnOuder1 = ouder1.getBurgerservicenummer();
        String bsnOuder2 = ouder2.getBurgerservicenummer();
        if (bsnOuder1 != null && bsnOuder2 != null) {
            return !bsnOuder1.isEmpty() && !bsnOuder2.isEmpty();
        } else {
            return false;
        }
    }

    public String hoeveelJuridischeOuders() {
        String geslachtsnaamOuder1 = ouder1 == null ? null : ouder1.getGeslachtsnaam();
        String geslachtsnaamOuder2 = ouder2 == null ? null : ouder2.getGeslachtsnaam();
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

        String ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        if (ingangsdatumGeldigheidGezag == null) return false;

        String datumIngangFamiliebetrekkingOuder1 = ouder1.getDatumIngangFamiliebetrekking();
        if (datumIngangFamiliebetrekkingOuder1 == null) return false;
        boolean ouder1AdoptieNa = Integer.parseInt(datumIngangFamiliebetrekkingOuder1) >= Integer.parseInt(ingangsdatumGeldigheidGezag);

        String datumIngangFamiliebetrekkingOuder2 = ouder2.getDatumIngangFamiliebetrekking();
        if (datumIngangFamiliebetrekkingOuder2 == null) return false;
        boolean ouder2AdoptieNa = Integer.parseInt(datumIngangFamiliebetrekkingOuder2) >= Integer.parseInt(ingangsdatumGeldigheidGezag);

        return ouder1AdoptieNa || ouder2AdoptieNa;
    }

    public boolean geadopteerdMetNlAkte() {
        var aktenummers = Stream.concat(
            Stream.of(persoon.getAktenummer()),
            geschiedenisPersoon.stream().map(GeschiedenisPersoon::getAktenummer)
        ).toList();
        var geldigeErkenningCodes = Set.of(TABEL_39_AKTEAANDUIDING_ADOPTIE);

        // controleer de lijst op de erkenningscodes uit de publieke tabel 39
        return controleerAkteNummers(aktenummers, geldigeErkenningCodes);
    }

    public boolean heefIndicatieGezag() {
        return gezagsverhouding != null
            && INDICATIE_GEZAG_CODES.contains(gezagsverhouding.getIndicatieGezagMinderjarige());
    }

    public boolean minderjarig() throws AfleidingsregelException {
        // PL 1/2 : 01.03.10
        if (persoon == null) throw new AfleidingsregelException("Preconditie: persoon mag niet leeg zijn", "persoon");

        var geboortedatumAsString = persoon.getGeboortedatum();
        int geboortedatum = Optional.ofNullable(geboortedatumAsString).map(Integer::parseInt).orElse(0);
        if (geboortedatum == 0)
            throw new AfleidingsregelException("Preconditie: geboortedatum mag niet onbekend zijn", "geboortedatum");

        int datumVolwassenVanaf = Integer.parseInt(LocalDate.now().format(FORMATTER)) - MEERDERJARIGE_LEEFTIJD;
        return geboortedatum > datumVolwassenVanaf;
    }
}
