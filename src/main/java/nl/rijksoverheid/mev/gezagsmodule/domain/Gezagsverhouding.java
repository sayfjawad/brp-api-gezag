package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

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

    public Gezagsverhouding(final Map<String, String> values, final Clock clock) {
        super(Categorie.GEZAGSVERHOUDING, values, clock);
    }

    public String getIndicatieGezagMinderjarige() {
        return get(INDICATIE_GEZAG_MINDERJARIGE);
    }

    public String getIndicatieCurateleRegister() {
        return get(INDICATIE_CURATELE_REGISTER);
    }

    public String getIngangsdatumGeldigheidGezag() {
        return get(INGANGSDATUM_GELDIGHEID_GEZAG);
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
