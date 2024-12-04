package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisOuder1 extends PotentieelInOnderzoek {

    private static final String AKTENUMMER = "528120";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "528310";
    private static final String ONDERZOEK_START_DATUM = "528320";
    private static final String ONDERZOEK_EIND_DATUM = "528330";

    public static GeschiedenisOuder1 from(Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(AKTENUMMER, lo3PlPersoonRecord.getAkteNr());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new GeschiedenisOuder1(values);
    }

    public GeschiedenisOuder1(final Map<String, String> values) {
        super(Categorie.GESCHIEDENIS_OUDER_1, values);
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer van ouder 1 (historie)");
    }

    @Override
    public String getCategorieName() {
        return "ouder 1 (historie)";
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