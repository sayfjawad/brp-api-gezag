package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis persoon
 */
public class GeschiedenisOuder2 extends PotentieelInOnderzoek {

    private static final String BSN = "530120";
    private static final String VOORNAMEN = "530210";
    private static final String VOORVOEGSEL = "530230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "530240";
    private static final String GEBOORTEDATUM = "530310";
    private static final String GEBOORTELAND = "530330";
    private static final String RNI_DEELNEMER = "538810";
    private static final String AKTENUMMER = "538120";
    private static final String DOCUMENT_BESCHRIJVING = "538230";

    public GeschiedenisOuder2(final Map<String, String> values, final Clock clock) {
        super(Categorie.GESCHIEDENIS_OUDER_2, values, clock);
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