package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Kind zoals opgenomen voor persoon in de persoonslijst
 */
public class Kind extends PotentieelInOnderzoek {

    public static final String BSN = "090120";
    public static final String GEBOORTEDATUM = "090310";
    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "098310";
    private static final String ONDERZOEK_START_DATUM = "098320";
    private static final String ONDERZOEK_EIND_DATUM = "098330";
    private static final int MEERDERJARIGE_LEEFTIJD = 180000;

    public static Kind from(Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(BSN, burgerServiceNrAsString);
        values.put(GEBOORTEDATUM, Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null));
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new Kind(values);
    }

    public Kind(final Map<String, String> values) {
        super(Categorie.KIND, values);
    }

    public String getBsn() {
        return get(BSN, "burgerservicenummer van kind");
    }

    public String getGeboortedatum() {
        return get(GEBOORTEDATUM, "geboortedatum van kind");
    }

    public boolean isMinderjarig() {
        String geboortedatum = getGeboortedatum();
        if (geboortedatum == null) {
            return false;
        }
        int minderjarigTotInt = Integer.parseInt(geboortedatum) + MEERDERJARIGE_LEEFTIJD;
        int datumVandaag = Integer.parseInt(LocalDate.now().format(FORMATTER));
        return minderjarigTotInt > datumVandaag;
    }

    @Override
    public String getCategorieName() {
        return "kind";
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getBsn(),
            getGeboortedatum(),
            getAanduidingGegevensInOnderzoek(),
            getDatumIngangOnderzoek(),
            getDatumEindeOnderzoek());
    }
}