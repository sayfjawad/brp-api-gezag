package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Inschrijving
 */
@Getter
@Categorie(number = "07", name = "inschrijving")
public class Inschrijving {

    //this parent object has no gegevens in onderzoek so here getFieldIfNotInOnderzoek will not be used on the getters
    // Datum waarop de bijhouding van de PL is gestaakt
    @VeldNummer(number = "076710")
    private final String datumOpschortingBijhouding;
    // Aanduiding van de reden waarom de bijhouding van de PL is opgeschort
    // E = Emigratie
    // M = Ministerieel besluit
    // O = Overlijden
    // F = Fout
    // . = (punt) Standaardwaarde indien onbekend
    @VeldNummer(number = "076720")
    private final String redenOpschortingBijhouding;

    public Inschrijving(final Lo3PlRecord lo3PlRecord) {
        datumOpschortingBijhouding = Objects.toString(lo3PlRecord.getBijhoudingOpschortDatum(), null);
        redenOpschortingBijhouding = lo3PlRecord.getBijhoudingOpschortReden();
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getDatumOpschortingBijhouding(),
            getRedenOpschortingBijhouding());
    }
}
