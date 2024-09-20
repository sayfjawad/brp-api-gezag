package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Huwelijk of partnerschap
 */
public class HuwelijkOfPartnerschap extends PotentieelInOnderzoek {

    private static final String BSN = "050120";
    /**
     * Datum waarop het huwelijk is voltrokken of het partnerschap is aangegaan.
     * <p>
     * yyyyMMdd formaat
     */
    private static final String DATUM_VOLTROKKEN = "050610";

    /**
     * Plaats waar het huwelijk is voltrokken of het partnerschap is aangegaan
     */
    private static final String PLAATS_VOLTROKKEN = "050620";

    /**
     * Land waar het huwelijk is voltrokken of het partnerschap is aangegaan
     */
    private static final String LAND_VOLTROKKEN = "050630";

    /**
     * Datum waarop het huwelijk/partnerschap is ontbonden of nietig verklaard.
     * <p>
     * yyyyMMdd formaat
     */
    private static final String DATUM_ONTBINDING = "050710";

    /**
     * Plaats waar huwelijk/partnerschap is ontbonden of nietig verklaard
     */
    private static final String PLAATS_ONTBINDING = "050720";

    /**
     * Land waar huwelijk/partnerschap is ontbonden of nietig verklaard
     */
    private static final String LAND_ONTBINDING = "050730";

    /**
     * Geeft aan om welke reden het partnerschap is ontbonden of nietig
     * verklaard
     * <p>
     * Code opgenomen in Tabel 41: Tabel Reden ontbinding/nietigverklaring
     * huwelijk/partnerschap
     *
     * @see <a href="https://publicaties.rvig.nl/Landelijke_tabellen">Tabel
     * Reden ontbinding/nietigverklaring huwelijk/partnerschap</a>
     */
    private static final String REDEN_ONTBINDING = "050740";

    private static final String ONDERZOEK_GEGEVENS_AANDUIDING = "058310";
    private static final String ONDERZOEK_START_DATUM = "058320";
    private static final String ONDERZOEK_EIND_DATUM = "058330";

    public static HuwelijkOfPartnerschap from(Lo3PlPersoonRecord lo3PlPersoonRecord, Clock clock) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        Map<String, String> values = new HashMap<>();
        values.put(BSN, burgerServiceNrAsString);
        values.put(DATUM_VOLTROKKEN, Objects.toString(lo3PlPersoonRecord.getRelatieStartDatum(), null));
        values.put(PLAATS_VOLTROKKEN, lo3PlPersoonRecord.getRelatieStartPlaats());
        values.put(LAND_VOLTROKKEN, Objects.toString(lo3PlPersoonRecord.getRelatieStartLandCode(), null));
        values.put(DATUM_ONTBINDING, Objects.toString(lo3PlPersoonRecord.getRelatieEindDatum(), null));
        values.put(PLAATS_ONTBINDING, lo3PlPersoonRecord.getRelatieEindPlaats());
        values.put(LAND_ONTBINDING, Objects.toString(lo3PlPersoonRecord.getRelatieEindLandCode(), null));
        values.put(REDEN_ONTBINDING, lo3PlPersoonRecord.getRelatieEindReden());
        values.put(ONDERZOEK_GEGEVENS_AANDUIDING, onderzoekGegevensAanduidingAsString);
        values.put(ONDERZOEK_START_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekStartDatum(), null));
        values.put(ONDERZOEK_EIND_DATUM, Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null));

        return new HuwelijkOfPartnerschap(values, clock);
    }

    public HuwelijkOfPartnerschap(final Map<String, String> values, final Clock clock) {
        super(Categorie.HUWELIJK_OF_PARTNERSCHAP, values, clock);
    }

    public String getBsnPartner() {
        return get(BSN, "BSN");
    }

    public String getDatumVoltrokken() {
        return get(DATUM_VOLTROKKEN, "datum voltrokken");
    }

    public String getPlaatsVoltrokken() {
        return get(PLAATS_VOLTROKKEN, "plaats voltrokken");
    }

    public String getLandVoltrokken() {
        return get(LAND_VOLTROKKEN, "land voltrokken");
    }

    public String getDatumOntbinding() {
        return get(DATUM_ONTBINDING, "datum ontbinding");
    }

    public String getPlaatsOntbinding() {
        return get(PLAATS_ONTBINDING, "plaats ontbinding");
    }

    public String getLandOntbinding() {
        return get(LAND_ONTBINDING, "land ontbinding");
    }

    public String getRedenOntbinding() {
        return get(REDEN_ONTBINDING, "reden ontbinding");
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBsnPartner(),
                getDatumVoltrokken(),
                getPlaatsVoltrokken(),
                getLandVoltrokken(),
                getDatumOntbinding(),
                getPlaatsOntbinding(),
                getLandOntbinding(),
                getRedenOntbinding(),
                getAanduidingGegevensInOnderzoek(),
                getDatumIngangOnderzoek(),
                getDatumEindeOnderzoek());
    }
}