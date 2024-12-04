package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Gezags verhouding
 */
public class Gezagsverhouding extends PotentieelInOnderzoek {
    
    // Een aanduiding die aangeeft wie belast is met het gezag over de
    // minderjarige ingeschrevene:
    // 1 = Ouder1 heeft het gezag
    // 2 = Ouder2 heeft het gezag
    // D = een of meer derden hebben het gezag
    // 1D = Ouder1 en een derde hebben het gezag
    // 2D = Ouder2 en een derde hebben het gezag
    // 12 = Ouder1 en Ouder2 hebben het gezag
    private static final String INDICATIE_GEZAG_MINDERJARIGE = "113210";
    // Aanduiding dat de ingeschrevene onder curatele is gestel
    private static final String INDICATIE_CURATELE_REGISTER = "113310";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "118310";
    private static final String ONDERZOEK_START_DATUM = "118320";
    private static final String ONDERZOEK_EIND_DATUM = "118330";
    // Datum waarop het geheel van gegevens geldig is geworden: yyyyMMdd formaat
    private static final String INGANGSDATUM_GELDIGHEID_GEZAG = "118510";

    private static final String INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE = "0";
    private static final String INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE_OLD = "00000000"; // only occurs during JSON-based acceptance tests

    public static Gezagsverhouding from(Lo3PlGezagsverhoudingRecord lo3PlGezagsverhoudingRecord) {
        var onderzoekGegevensAanduiding = lo3PlGezagsverhoudingRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(INDICATIE_GEZAG_MINDERJARIGE, lo3PlGezagsverhoudingRecord.getMinderjarigGezagInd());
        values.put(INDICATIE_CURATELE_REGISTER, Objects.toString(lo3PlGezagsverhoudingRecord.getCurateleRegisterInd(), null));
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlGezagsverhoudingRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlGezagsverhoudingRecord.getOnderzoekEindDatum(), null));
        values.put(INGANGSDATUM_GELDIGHEID_GEZAG, Objects.toString(lo3PlGezagsverhoudingRecord.getGeldigheidStartDatum(), null));

        return new Gezagsverhouding(values);
    }

    public Gezagsverhouding(final Map<String, String> values) {
        super(Categorie.GEZAGSVERHOUDING, values);
    }

    /**
     * Returns true if <i>ingangsdatum geldigheid gezag</i> is valid; otherwise false.
     * <p>
     * <i>Ingangsdatum geldigheid gezag</i> is valid if it is nonnull and does not contain the default value {@code 0}.
     *
     * @return true if <i>ingangsdatum geldigheid gezag</i> is valid; otherwise false.
     */
    public boolean hasIngangsdatumGeldigheidGezag() {
        String ingangsdatumGeldigheidGezag = getIngangsdatumGeldigheidGezag();
        if (ingangsdatumGeldigheidGezag == null) return false;

        ingangsdatumGeldigheidGezag = ingangsdatumGeldigheidGezag.equals(INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE_OLD)
            ? INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE
            : ingangsdatumGeldigheidGezag;

        return !ingangsdatumGeldigheidGezag.equals(INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE);
    }

    public String getIndicatieGezagMinderjarige() {
        return get(INDICATIE_GEZAG_MINDERJARIGE, "indicatie gezag minderjarige");
    }

    public String getIndicatieCurateleRegister() {
        return get(INDICATIE_CURATELE_REGISTER, "indicatie curatele register");
    }

    public String getIngangsdatumGeldigheidGezag() {
        return get(INGANGSDATUM_GELDIGHEID_GEZAG, "ingangsdatum geldigheid gezag");
    }

    @Override
    public String getCategorieName() {
        return "gezagsverhouding";
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getIndicatieGezagMinderjarige(),
                getIndicatieCurateleRegister(),
                getIngangsdatumGeldigheidGezag(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}