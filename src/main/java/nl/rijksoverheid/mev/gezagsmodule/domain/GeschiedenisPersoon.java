package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisPersoon extends PotentieelInOnderzoek {

    private static final String BSN = "510120";
    private static final String VOORNAMEN = "510210";
    private static final String VOORVOEGSEL = "510230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "510240";
    private static final String GEBOORTEDATUM = "510310";
    private static final String GEBOORTELAND = "510330";
    private static final String RNI_DEELNEMER = "518810";
    private static final String AKTENUMMER = "518120";
    private static final String DOCUMENT_BESCHRIJVING = "518230";

    public GeschiedenisPersoon(final Map<String, String> values, final Clock clock) {
        super(Categorie.GESCHIEDENIS_PERSOON, values, clock);
    }

    public String getBsn() {
        return get(BSN, "BSN");
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

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum");
    }

    public String getGeboorteland() {
        return get(GEBOORTELAND, "geboorteland");
    }

    public String getAktenummer() {
        return get(AKTENUMMER, "aktenummer");
    }

    public String getDocumentBeschrijving() {
        return get(DOCUMENT_BESCHRIJVING, "document beschrijving");
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