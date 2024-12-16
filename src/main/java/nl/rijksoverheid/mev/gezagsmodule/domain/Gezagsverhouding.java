package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Gezags verhouding
 */
@Categorie(number = "11", name = "gezagsverhouding")
public class Gezagsverhouding extends PotentieelInOnderzoek {
    
    // Een aanduiding die aangeeft wie belast is met het gezag over de
    // minderjarige ingeschrevene:
    // 1 = Ouder1 heeft het gezag
    // 2 = Ouder2 heeft het gezag
    // D = een of meer derden hebben het gezag
    // 1D = Ouder1 en een derde hebben het gezag
    // 2D = Ouder2 en een derde hebben het gezag
    // 12 = Ouder1 en Ouder2 hebben het gezag
    @VeldNummer(number = "113210", name = "indicatie gezag minderjarige")
    private final String indicatieGezagMinderjarige;

    // Aanduiding dat de ingeschrevene onder curatele is gestel
    @VeldNummer(number = "113310", name = "indicatie curatele register")
    private final String indicatieCurateleRegister;

    // Datum waarop het geheel van gegevens geldig is geworden: yyyyMMdd formaat
    @VeldNummer(number = "118510", name = "ingangsdatum geldigheid gezag")
    private final String ingangsDatumGeldigheidGezag;

    private static final String INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE = "0";
    private static final String INGANGSDATUM_GELDIGHEID_GEZAG_DEFAULT_VALUE_OLD = "00000000"; // only occurs during JSON-based acceptance tests

    public Gezagsverhouding(final Lo3PlGezagsverhoudingRecord lo3PlGezagsverhoudingRecord) {
        var onderzoekGegevensAanduiding = lo3PlGezagsverhoudingRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        indicatieGezagMinderjarige = lo3PlGezagsverhoudingRecord.getMinderjarigGezagInd();
        indicatieCurateleRegister = Objects.toString(lo3PlGezagsverhoudingRecord.getCurateleRegisterInd(), null);
        aanduidingGegevensInOnderzoek = onderzoekGegevensAanduidingAsString;
        datumEindeOnderzoek = Objects.toString(lo3PlGezagsverhoudingRecord.getOnderzoekEindDatum(), null);
        ingangsDatumGeldigheidGezag = Objects.toString(lo3PlGezagsverhoudingRecord.getGeldigheidStartDatum(), null);
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
        registerIfInOnderzoek("indicatieGezagMinderjarige", getClass());

        return indicatieGezagMinderjarige;
    }

    public String getIndicatieCurateleRegister() {
        registerIfInOnderzoek("indicatieCurateleRegister", getClass());

        return indicatieCurateleRegister;
    }

    public String getIngangsdatumGeldigheidGezag() {
        registerIfInOnderzoek("ingangsDatumGeldigheidGezag", getClass());

        return ingangsDatumGeldigheidGezag;
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
                getDatumEindeOnderzoek());
    }
}