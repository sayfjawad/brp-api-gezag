package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Ouder1 voor persoon
 */
public class Ouder1 extends PotentieelInOnderzoek {

    private static final String BSN = "020120";
    private static final String VOORNAMEN = "020210";
    private static final String VOORVOEGSEL = "020230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "020240";
    private static final String AKTENUMMER = "028120";
    private static final String DATUM_INGANG_FAMILIEBETREKKING = "026210"; // yyyMMdd formaat

    public Ouder1(final Map<String, String> values, final Clock clock) {
        super(Categorie.OUDER_1, values, clock);
    }

    public String getBsn() {
        return get(BSN, "bsn");
    }

    public String getVoornamen() {
        return get(VOORNAMEN, "voornamen");
    }

    public String getVoorvoegsel() {
        return get(VOORVOEGSEL, "voorvoegsel");
    }

    public String getGeslachtsnaam() {
        return get(GESLACHTSNAAM, "geslachtsnaam");
    }

    public String getDatumIngangFamiliebetrekking() {
        return get(DATUM_INGANG_FAMILIEBETREKKING, "datum ingang familiebetrekking");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer");
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