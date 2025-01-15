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

    private static final int MEERDERJARIGE_LEEFTIJD = 180000;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String GEEN_OUDERS = "Geen_ouders";
    private static final String EEN_OUDER = "Een_ouder";
    private static final String TWEE_OUDERS = "Twee_ouders";
    private static final String GESLACHTNAAM_AANDUIDING_PUNTOUDER = ".";
    private static final String PUNTOUDERS = "1_of_2_puntouders";
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

    public Set<String> getUsedVeldenInOnderzoek() {
        return Stream.of(
                Optional.ofNullable(persoon).stream().map(Persoon::getVeldenInOnderzoek).flatMap(Collection::stream),
                geschiedenisPersoon.stream().map(GeschiedenisPersoon::getVeldenInOnderzoek).flatMap(Set::stream),
                Optional.ofNullable(gezagsverhouding).stream().map(Gezagsverhouding::getVeldenInOnderzoek).flatMap(Collection::stream),
                huwelijkOfPartnerschappen.stream().map(HuwelijkOfPartnerschap::getVeldenInOnderzoek).flatMap(Set::stream),
                Optional.ofNullable(ouder1).stream().map(Ouder1::getVeldenInOnderzoek).flatMap(Collection::stream),
                geschiedenisOuder1.stream().map(GeschiedenisOuder1::getVeldenInOnderzoek).flatMap(Set::stream),
                Optional.ofNullable(ouder2).stream().map(Ouder2::getVeldenInOnderzoek).flatMap(Collection::stream),
                geschiedenisOuder2.stream().map(GeschiedenisOuder2::getVeldenInOnderzoek).flatMap(Set::stream))
            .flatMap(Function.identity())
            .collect(Collectors.toSet());
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

    public boolean isOverleden() {
        return inschrijving != null
            && inschrijving.getDatumOpschortingBijhouding() != null
            && "O".equals(inschrijving.getRedenOpschortingBijhouding());
    }

    public boolean isNietIngeschrevenInRNI() {
        // PL 1/2 : 07.67.20
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "R"));
    }

    public boolean isNietGeemigreerd() {
        // PL 1/2 : 07.67.20
        return !(inschrijving != null && Objects.equals(inschrijving.getRedenOpschortingBijhouding(), "E"));
    }

    public boolean onderCurateleGesteld() {
        return gezagsverhouding != null
            && isNotBlank(gezagsverhouding.getIndicatieCurateleRegister());
    }

    public Optional<Character> isOverledenOfOnbevoegdEncoded() {
        if (isOverleden()) return Optional.of('o');
        if (onderCurateleGesteld()) return Optional.of('c');
        if (minderjarig()) return Optional.of('m');

        return Optional.empty();
    }

    public boolean isOverledenOfOnbevoegd() throws AfleidingsregelException {
        return isOverleden() || minderjarig() || onderCurateleGesteld();
    }

    public boolean alsMinderjarigeOverleden() throws AfleidingsregelException {
        if (isOverleden()) {
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

    public boolean ongeborenVruchtErkendOfGerechtelijkeVaststelling() {
        return AktenummerAfleiding
            .ongeborenVruchtErkendOfGerechtelijkeVaststelling(persoon, new ArrayList<>(geschiedenisPersoon));
    }


    public boolean ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling() {
        return AktenummerAfleiding
            .ongeborenVruchtErkendOfGerechtelijkeVaststelling(ouder1, new ArrayList<>(geschiedenisOuder1));
    }

    public boolean ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling() {
        return AktenummerAfleiding
            .ongeborenVruchtErkendOfGerechtelijkeVaststelling(ouder2, new ArrayList<>(geschiedenisOuder2));
    }


    public boolean ontkenningOuderschapDoorOuder1() {
        return AktenummerAfleiding
            .ontkenningOuderschapDoorOuder(ouder1, new ArrayList<>(geschiedenisOuder1));
    }

    public boolean ontkenningOuderschapDoorOuder2() {
        return AktenummerAfleiding
            .ontkenningOuderschapDoorOuder(ouder2, new ArrayList<>(geschiedenisOuder2));
    }

    public boolean ongeborenVruchtErkend() {
        return AktenummerAfleiding
            .ongeborenVruchtErkend(persoon, new ArrayList<>(geschiedenisPersoon));
    }

    public boolean geadopteerdMetNlAkte() {
        return AktenummerAfleiding
            .geadopteerdMetNlAkte(persoon, new ArrayList<>(geschiedenisPersoon));
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

    public Optional<Ouder1> getOuder1AsOptional() {
        return Optional.ofNullable(getOuder1());
    }

    public Optional<Ouder2> getOuder2AsOptional() {
        return Optional.ofNullable(getOuder2());
    }
}
