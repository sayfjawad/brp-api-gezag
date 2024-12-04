package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Persoon (vanuit persoonslijst zoals opgenomen in de BRP)
 */
public class Persoon extends PotentieelInOnderzoek {

    public static final String BSN = "010120";
    private static final String VOORNAMEN = "010210";
    private static final String VOORVOEGSEL = "010230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "010240";
    private static final String GEBOORTEDATUM = "010310";
    private static final String GEBOORTELAND = "010330";
    private static final String AKTENUMMER = "018120";
    private static final String DOCUMENT_BESCHRIJVING = "018230";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "018310";
    private static final String ONDERZOEK_START_DATUM = "018320";
    private static final String ONDERZOEK_EIND_DATUM = "018330";

    private static final String RNI_DEELNEMER = "018810";

    public static Persoon from(Lo3PlPersoonRecord lo3PlPersoonRecord, Clock clock) {
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
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(DOCUMENT_BESCHRIJVING, lo3PlPersoonRecord.getDocBeschrijving());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));
        values.put(RNI_DEELNEMER, Objects.toString(lo3PlPersoonRecord.getRniDeelnemer(), null));

        return new Persoon(values, clock);
    }

    public Persoon(final Map<String, String> values, final Clock clock) {
        super(Categorie.PERSOON, values, clock);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van persoon");
    }

    public String getVoornamen() {
        return get(VOORNAMEN, "voornamen van persoon");
    }

    public String getVoorvoegsel() {
        return get(VOORVOEGSEL, "voorvoegsel van persoon");
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM, "geslachtsnaam van persoon");
    }

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum van persoon");
    }

    public String getGeboorteland() {
        return get(GEBOORTELAND, "geboorteland van persoon");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van persoon");
    }

    public String getDocumentBeschrijving() {
        return get(DOCUMENT_BESCHRIJVING, "document beschrijving van persoon");
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER, "RNI deelnemer van persoon");
    }

    @Override
    public String getCategorieName() {
        return "persoon";
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