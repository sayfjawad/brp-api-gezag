package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Ouder2 voor persoon
 */
public class Ouder2 extends PotentieelInOnderzoek {

    private static final String BSN = "030120";
    private static final String VOORNAMEN = "030210";
    private static final String VOORVOEGSEL = "030230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "030240";
    private static final String AKTENUMMER = "038120";
    private static final String DATUM_INGANG_FAMILIEBETREKKING = "036210"; // yyyMMdd formaat

    public Ouder2(final Map<String, String> values, final Clock clock) {
        super(Categorie.OUDER_2, values, clock);
    }

    public String getBsn() {
        return get(BSN);
    }

    public String getVoornamen() {
        return get(VOORNAMEN);
    }

    public String getVoorvoegsel() {
        return get(VOORVOEGSEL);
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM);
    }

    public String getDatumIngangFamiliebetrekking() {
        return get(DATUM_INGANG_FAMILIEBETREKKING);
    }

    public String getAktenummer() {
        return get(AKTENUMMER);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBsn(),
                getVoornamen(),
                getVoorvoegsel(),
                getGeslachtsnaam(),
                getDatumIngangFamiliebetrekking(),
                getAktenummer(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}