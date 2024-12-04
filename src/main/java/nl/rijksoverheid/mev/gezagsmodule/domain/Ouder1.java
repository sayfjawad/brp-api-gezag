package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Ouder1 voor persoon
 */
public class Ouder1 extends PotentieelInOnderzoek {

    private static final String BSN = "020120";
    private static final String GESLACHTSNAAM = "020240";
    private static final String AKTENUMMER = "028120";
    private static final String DATUM_INGANG_FAMILIEBETREKKING = "026210"; // yyyMMdd formaat
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "028310";
    private static final String ONDERZOEK_START_DATUM = "028320";
    private static final String ONDERZOEK_EIND_DATUM = "028330";

    public static Ouder1 from(Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(BSN, burgerServiceNrAsString);
        values.put(GESLACHTSNAAM, lo3PlPersoonRecord.getGeslachtsNaam());
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(DATUM_INGANG_FAMILIEBETREKKING, Objects.toString(lo3PlPersoonRecord.getFamilieBetrekStartDatum(), null));
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new Ouder1(values);
    }

    public Ouder1(final Map<String, String> values) {
        super(Categorie.OUDER_1, values);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van ouder 1");
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM, "geslachtsnaam van ouder 1");
    }

    public String getDatumIngangFamiliebetrekking() {
        return get(DATUM_INGANG_FAMILIEBETREKKING, "datum ingang familiebetrekking van ouder 1");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van ouder 1");
    }

    @Override
    public String getCategorieName() {
        return "ouder 1";
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBsn(),
                getGeslachtsnaam(),
                getDatumIngangFamiliebetrekking(),
                getAktenummer(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}