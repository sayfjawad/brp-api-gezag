package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlVerblijfplaatsRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Verblijfplaats van de persoon
 */
public class Verblijfplaats extends PotentieelInOnderzoek {

    // Een code, opgenomen in Tabel 33 'Gemeentetabel' op naam, die aangeeft in welke gemeente de PL zich bevindt
    // of de gemeente waarnaar de PL is uitgeschreven of de gemeente waar de PL voor de eerste keer is opgenomen.
    // 0599 = Rotterdam
    // 1999 = Registratie Niet Ingezetenen (RNI)   
    private static final String GEMEENTE_VAN_INSCHRIJVING = "080910";
    // Een code, opgenomen in Tabel 34 'Landentabel' op naam, die het land aangeeft waar de ingeschrevene verblijf
    // hield voor (her)vestiging in Nederland
    private static final String LAND_VANWAAR_INGESCHREVEN = "081410";
    private static final String DATUM_VESTIGING_IN_NEDERLAND = "081420";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "088310";
    private static final String ONDERZOEK_START_DATUM = "088320";
    private static final String ONDERZOEK_EIND_DATUM = "088330";
    private static final String RNI_DEELNEMER = "088810";

    public static Verblijfplaats from(Lo3PlVerblijfplaatsRecord lo3PlVerblijfplaatsRecord, Clock clock) {
        var onderzoekGegevensAanduiding = lo3PlVerblijfplaatsRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(GEMEENTE_VAN_INSCHRIJVING, Objects.toString(lo3PlVerblijfplaatsRecord.getInschrijvingGemeenteCode(), null));
        values.put(LAND_VANWAAR_INGESCHREVEN, Objects.toString(lo3PlVerblijfplaatsRecord.getVestigingLandCode(), null));
        values.put(DATUM_VESTIGING_IN_NEDERLAND, Objects.toString(lo3PlVerblijfplaatsRecord.getVestigingDatum(), null));
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlVerblijfplaatsRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlVerblijfplaatsRecord.getOnderzoekEindDatum(), null));
        values.put(RNI_DEELNEMER, Objects.toString(lo3PlVerblijfplaatsRecord.getRniDeelnemer(), null));

        return new Verblijfplaats(values, clock);
    }

    public Verblijfplaats(final Map<String, String> values, final Clock clock) {
        super(Categorie.VERBLIJFPLAATS, values, clock);
    }

    @Override
    public String get(final String key) {
        return values.get(key);
    }

    @Override
    public String get(final String key, final String fieldName) {
        return values.get(key);
    }

    public String getGemeenteVanInschrijving() {
        return get(GEMEENTE_VAN_INSCHRIJVING, "gemeente van inschrijving");
    }

    public String getLandVanwaarIngeschreven() {
        return get(LAND_VANWAAR_INGESCHREVEN, "land vanwaar ingeschreven");
    }

    public String getDatumVestigingInNederland() {
        return get(DATUM_VESTIGING_IN_NEDERLAND, "datum vestiging in nederland");
    }

    public String getRniDeelnemer() {
        return get(RNI_DEELNEMER, "RNI deelnemer");
    }

    @Override
    public String getCategorieName() {
        return "verblijfplaats";
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getGemeenteVanInschrijving(),
            getLandVanwaarIngeschreven(),
            getDatumVestigingInNederland(),
            getRniDeelnemer(),
            getAanduidingGegevensInOnderzoek(),
            getDatumIngangOnderzoek(),
            getDatumEindeOnderzoek());

    }
}
