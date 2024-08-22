package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlGezagsverhoudingRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
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
    // Datum waarop het geheel van gegevens geldig is geworden: yyyyMMdd formaat
    private static final String INGANGSDATUM_GELDIGHEID_GEZAG = "118510";

    public static Gezagsverhouding from(Lo3PlGezagsverhoudingRecord lo3PlGezagsverhoudingRecord, Clock clock) {
        Map<String, String> values = new HashMap<>();
        values.put(INDICATIE_GEZAG_MINDERJARIGE, lo3PlGezagsverhoudingRecord.getMinderjarigGezagInd());
        values.put(INDICATIE_CURATELE_REGISTER, Objects.toString(lo3PlGezagsverhoudingRecord.getCurateleRegisterInd(), null));
        values.put(INGANGSDATUM_GELDIGHEID_GEZAG, Objects.toString(lo3PlGezagsverhoudingRecord.getGeldigheidStartDatum(), null));

        return new Gezagsverhouding(values, clock);
    }

    public Gezagsverhouding(final Map<String, String> values, final Clock clock) {
        super(Categorie.GEZAGSVERHOUDING, values, clock);
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