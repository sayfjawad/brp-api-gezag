package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

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
    private static final String RNI_DEELNEMER = "088810";

    public Verblijfplaats(final Map<String, String> values, final Clock clock) {
        super(Categorie.VERBLIJFPLAATS, values, clock);
    }

    @Override
    public String get(final String key) {
        return values.get(key);
    }

    @Override
    public String get(final String key, final String fieldName) {
        //inOnderzoek(key, fieldName);

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
