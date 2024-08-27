package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlRecord;
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

    public static Inschrijving from(final Lo3PlRecord lo3PlRecord) {
        Map<String, String> values = new HashMap<>();
        values.put(DATUM_OPSCHORTING_BIJHOUDING, Objects.toString(lo3PlRecord.getBijhoudingOpschortDatum()));
        values.put(REDEN_OPSCHORTING_BIJHOUDING, lo3PlRecord.getBijhoudingOpschortReden());
        values.put(DATUM_VERIFICATIE, Objects.toString(lo3PlRecord.getVerificatieDatum()));
        values.put(OMSCHRIJVING_VERIFICATIE, lo3PlRecord.getVerificatieOms());
        values.put(RNI_DEELNEMER, Objects.toString(lo3PlRecord.getRniDeelnemer()));

        return new Inschrijving(values);
    }

    @Override
    public String get(final String key) {
        return values.get(key);
    }

    @Override
    public String get(final String key, final String fieldName) {
        return values.get(key);
    }
    
    public String getDatumOpschortingBijhouding() {
        return get(DATUM_OPSCHORTING_BIJHOUDING, "datum opschroting bijhouding");
    }

    public String getRedenOpschortingBijhouding() {
        return get(REDEN_OPSCHORTING_BIJHOUDING, "reden opschorting bijhouding");
    }

    public String getDatumVerificatie() {
        return get(DATUM_VERIFICATIE, "datum verificatie");
    }

    public String getOmschrijvingVerificatie() {
        return get(OMSCHRIJVING_VERIFICATIE, "omschrijving verificatie");
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER, "RNI deelnemer");
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
