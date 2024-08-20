package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Kind zoals opgenomen voor persoon in de persoonslijst
 */
public class Kind extends PotentieelInOnderzoek {

    public static final String BSN = "090120";
    private static final String VOORNAMEN = "090210";
    private static final String VOORVOEGSEL = "090230";// Voorvoegsel geslachtsnaam persoon
    private static final String GESLACHTSNAAM = "090240";
    public static final String GEBOORTEDATUM = "090310";
    private static final int MEERDERJARIGE_LEEFTIJD = 180000;

    public static Kind from(Lo3PlPersoonRecord lo3PlPersoonRecord, Clock clock) {
        Map<String, String> values = new HashMap<>();
        values.put(BSN, Objects.toString(lo3PlPersoonRecord.getBurgerServiceNr(), null));
        values.put(VOORNAMEN, lo3PlPersoonRecord.getVoorNaam());
        values.put(VOORVOEGSEL, lo3PlPersoonRecord.getGeslachtsNaamVoorvoegsel());
        values.put(GESLACHTSNAAM, lo3PlPersoonRecord.getGeslachtsNaam());
        values.put(GEBOORTEDATUM, Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null));

        return new Kind(values, clock);
    }

    public Kind(final Map<String, String> values, final Clock clock) {
        super(Categorie.KIND, values, clock);
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

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum");
    }

    public boolean isMinderjarig() {
        String geboortedatum = getGeboortedatum();
        if (geboortedatum == null) {
            return false;
        }
        int minderjarigTotInt = Integer.parseInt(geboortedatum) + MEERDERJARIGE_LEEFTIJD;
        int datumVandaag = Integer.parseInt(LocalDate.now(clock).format(FORMATTER));
        return minderjarigTotInt > datumVandaag;
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
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}