package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Persoon (vanuit persoonslijst zoals opgenomen in de BRP)
 */
public class Persoon extends PotentieelInOnderzoek {

    public static final String BSN = "010120";
    private static final String VOORNAMEN = "010210";
    private static final String VOORVOEGSEL = "010230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "010240";
    private static final String GEBOORTEDATUM = "010310";
    private static final String GEBOORTELAND = "010330";
    private static final String AKTENUMMER = "018120";
    private static final String DOCUMENT_BESCHRIJVING = "018230";
    private static final String RNI_DEELNEMER = "018810";

    public Persoon(final Map<String, String> values, final Clock clock) {
        super(Categorie.PERSOON, values, clock);
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

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM);
    }

    public String getGeboorteland() {
        return get(GEBOORTELAND);
    }

    public String getAktenummer() {
        return get(AKTENUMMER);
    }

    public String getDocumentBeschrijving() {
        return get(DOCUMENT_BESCHRIJVING);
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
                getBsn(),
                getVoornamen(),
                getVoorvoegsel(),
                getGeslachtsnaam(),
                getGeboortedatum(),
                getGeboorteland(),
                getAktenummer(),
                getDocumentBeschrijving(),
                getRniDeelnemer(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}
