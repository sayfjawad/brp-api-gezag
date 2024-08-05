package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

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