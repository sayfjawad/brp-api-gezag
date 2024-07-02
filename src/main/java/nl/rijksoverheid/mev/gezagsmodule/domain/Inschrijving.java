package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PersoonslijstVeld;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Inschrijving
 */
public class Inschrijving implements PersoonslijstVeld {

    private final Map<String, String> values;

    //this parent object has no gegevens in onderzoek so here getFieldIfNotInOnderzoek will not be used on the getters
    // Datum waarop de bijhouding van de PL is gestaakt
    private static final String DATUM_OPSCHORTING_BIJHOUDING = "076710";
    // Aanduiding van de reden waarom de bijhouding van de PL is opgeschort
    // E = Emigratie
    // M = Ministerieel besluit
    // O = Overlijden
    // F = Fout
    // . = (punt) Standaardwaarde indien onbekend
    private static final String REDEN_OPSCHORTING_BIJHOUDING = "076720";
    private static final String DATUM_VERIFICATIE = "077110";
    private static final String OMSCHRIJVING_VERIFICATIE = "077120";
    private static final String RNI_DEELNEMER = "078810";

    public Inschrijving(final Map<String, String> values) {
        this.values = values;
    }

    @Override
    public String get(final String key) {
        return values.get(key);
    }

    public String getDatumOpschortingBijhouding() {
        return get(DATUM_OPSCHORTING_BIJHOUDING);
    }

    public String getRedenOpschortingBijhouding() {
        return get(REDEN_OPSCHORTING_BIJHOUDING);
    }

    public String getDatumVerificatie() {
        return get(DATUM_VERIFICATIE);
    }

    public String getOmschrijvingVerificatie() {
        return get(OMSCHRIJVING_VERIFICATIE);
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getDatumOpschortingBijhouding(),
                getRedenOpschortingBijhouding(),
                getDatumVerificatie(),
                getOmschrijvingVerificatie(),
                getRniDeelnemer());
    }
}
