package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisOuder2 extends PotentieelInOnderzoek {

    private static final String AKTENUMMER = "538120";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "538310";
    private static final String ONDERZOEK_START_DATUM = "538320";
    private static final String ONDERZOEK_EIND_DATUM = "538330";

    public static GeschiedenisOuder2 from(Lo3PlPersoonRecord lo3PlPersoonRecord) {

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new GeschiedenisOuder2(values);
    }

    public GeschiedenisOuder2(final Map<String, String> values) {
        super(Categorie.GESCHIEDENIS_OUDER_2, values);
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van ouder 2");
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
                getAktenummer(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}