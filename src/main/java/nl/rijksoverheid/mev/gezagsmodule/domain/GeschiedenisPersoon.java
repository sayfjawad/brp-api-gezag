package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisPersoon extends PotentieelInOnderzoek {

    private static final String BSN = "510120";
    private static final String VOORNAMEN = "510210";
    private static final String VOORVOEGSEL = "510230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "510240";
    private static final String GEBOORTEDATUM = "510310";
    private static final String GEBOORTELAND = "510330";
    private static final String RNI_DEELNEMER = "518810";
    private static final String AKTENUMMER = "518120";
    private static final String DOCUMENT_BESCHRIJVING = "518230";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "518310";
    private static final String ONDERZOEK_START_DATUM = "518320";
    private static final String ONDERZOEK_EIND_DATUM = "518330";

    public static GeschiedenisPersoon from(final Lo3PlPersoonRecord lo3PlPersoonRecord, final Clock clock) {
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

        return new GeschiedenisPersoon(values, clock);
    }

    public GeschiedenisPersoon(final Map<String, String> values, final Clock clock) {
        super(Categorie.GESCHIEDENIS_PERSOON, values, clock);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van persoon (historie)");
    }

    public String getVoornamen() {
        return get(VOORNAMEN, "voornamen van persoon (historie)");
    }

    public String getVoorvoegsel() {
        return get(VOORVOEGSEL, "voorvoegsel van persoon (historie)");
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM, "geslachtsnaam van persoon (historie)");
    }

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum van persoon (historie)");
    }

    public String getGeboorteland() {
        return get(GEBOORTELAND, "geboorteland van persoon (historie)");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van persoon (historie)");
    }

    public String getDocumentBeschrijving() {
        return get(DOCUMENT_BESCHRIJVING, "document beschrijving van persoon (historie)");
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER, "RNI deelnemer van persoon (historie)");
    }

    @Override
    public String getCategorieName() {
        return "persoon (historie)";
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