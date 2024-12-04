package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Persoon (vanuit persoonslijst zoals opgenomen in de BRP)
 */
public class Persoon extends PotentieelInOnderzoek {

    public static final String BSN = "010120";
    private static final String GEBOORTEDATUM = "010310";
    private static final String GEBOORTELAND = "010330";
    private static final String AKTENUMMER = "018120";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "018310";
    private static final String ONDERZOEK_START_DATUM = "018320";
    private static final String ONDERZOEK_EIND_DATUM = "018330";

    public static Persoon from(Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(BSN, burgerServiceNrAsString);
        values.put(GEBOORTEDATUM, Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null));
        values.put(GEBOORTELAND, Objects.toString(lo3PlPersoonRecord.getGeboorteLandCode(), null));
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new Persoon(values);
    }

    public Persoon(final Map<String, String> values) {
        super(Categorie.PERSOON, values);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van persoon");
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
            getGeboortedatum(),
            getGeboorteland(),
            getAktenummer(),
            getAanduidingGegevensInOnderzoek(),
            getDatumIngangOnderzoek(),
            getDatumEindeOnderzoek());
    }
}