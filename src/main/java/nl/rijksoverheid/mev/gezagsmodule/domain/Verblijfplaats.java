package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlVerblijfplaatsRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Verblijfplaats van de persoon
 */
@Getter
@Categorie(number = "08", name = "verblijfplaats")
public class Verblijfplaats {

    // Een code, opgenomen in Tabel 33 'Gemeentetabel' op naam, die aangeeft in welke gemeente de PL zich bevindt
    // of de gemeente waarnaar de PL is uitgeschreven of de gemeente waar de PL voor de eerste keer is opgenomen.
    // 0599 = Rotterdam
    // 1999 = Registratie Niet Ingezetenen (RNI)
    @VeldNummer(number = "080910")
    private final String gemeenteVanInschrijving;
    @VeldNummer(number = "081420")
    private final String datumVestigingInNederland;

    public Verblijfplaats(final Lo3PlVerblijfplaatsRecord lo3PlVerblijfplaatsRecord) {
        gemeenteVanInschrijving = Objects.toString(lo3PlVerblijfplaatsRecord.getInschrijvingGemeenteCode(), null);
        datumVestigingInNederland = Objects.toString(lo3PlVerblijfplaatsRecord.getVestigingDatum(), null);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getGemeenteVanInschrijving(),
            getDatumVestigingInNederland());
    }
}
