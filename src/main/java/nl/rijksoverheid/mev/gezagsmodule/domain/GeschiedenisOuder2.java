package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisOuder2 extends PotentieelInOnderzoek {

    private static final String BSN = "530120";
    private static final String VOORNAMEN = "530210";
    private static final String VOORVOEGSEL = "530230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "530240";
    private static final String GEBOORTEDATUM = "530310";
    private static final String GEBOORTELAND = "530330";
    private static final String RNI_DEELNEMER = "538810";
    private static final String AKTENUMMER = "538120";
    private static final String DOCUMENT_BESCHRIJVING = "538230";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "538310";
    private static final String ONDERZOEK_START_DATUM = "538320";
    private static final String ONDERZOEK_EIND_DATUM = "538330";

    public static GeschiedenisOuder2 from(Lo3PlPersoonRecord lo3PlPersoonRecord, Clock clock) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(BSN, burgerServiceNrAsString);
        values.put(VOORNAMEN, lo3PlPersoonRecord.getVoorNaam());
        values.put(VOORVOEGSEL, lo3PlPersoonRecord.getGeslachtsNaamVoorvoegsel());
        values.put(GESLACHTSNAAM, lo3PlPersoonRecord.getGeslachtsNaam());
        values.put(GEBOORTEDATUM, Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null));
        values.put(GEBOORTELAND, Objects.toString(lo3PlPersoonRecord.getGeboorteLandCode(), null));
        values.put(RNI_DEELNEMER, Objects.toString(lo3PlPersoonRecord.getRniDeelnemer(), null));
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(DOCUMENT_BESCHRIJVING, lo3PlPersoonRecord.getDocBeschrijving());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new GeschiedenisOuder2(values, clock);
    }

    public GeschiedenisOuder2(final Map<String, String> values, final Clock clock) {
        super(Categorie.GESCHIEDENIS_OUDER_2, values, clock);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van ouder 2");
    }

    public String getVoornamen() {
        return get(VOORNAMEN, "voornamen van ouder 2");
    }

    public String getVoorvoegsel() {
        return get(VOORVOEGSEL, "voorvoegsel van ouder 2");
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM, "geslachtsnaam van ouder 2");
    }

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum van ouder 2");
    }

    public String getGeboorteland() {
        return get(GEBOORTELAND, "geboorteland van ouder 2");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van ouder 2");
    }

    public String getDocumentBeschrijving() {
        return get(DOCUMENT_BESCHRIJVING, "document beschrijving van ouder 2");
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER, "RNI deelnemer van ouder 2");
    }

    @Override
    public String getCategorieName() {
        return "ouder 2 (historie)";
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBsn(),
                getVoornamen(),
                getVoorvoegsel(),
                getGeslachtsnaam(),
                getGeboortedatum(),
                getGeboorteland(),
                getAktenummer(),
                getDocumentBeschrijving(),
                getRniDeelnemer(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}