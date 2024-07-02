package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PotentieelInOnderzoek;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Geschiedenis huwelijk of partnerschap
 */
public class GeschiedenisHuwelijkOfPartnerschap extends PotentieelInOnderzoek {

    private static final String BSN = "550120";
    // Datum waarop het huwelijk is voltrokken of het partnerschap is aangegaan. yyyyMMdd formaat
    private static final String DATUM_VOLTROKKEN = "550610";
    // Plaats waar het huwelijk is voltrokken of het partnerschap is aangegaan
    private static final String PLAATS_VOLTROKKEN = "550620";
    // Land waar het huwelijk is voltrokken of het partnerschap is aangegaan
    private static final String LAND_VOLTROKKEN = "550640";
    // Datum waarop het huwelijk/partnerschap is ontbonden of nietig verklaard. yyyyMMdd formaat
    private static final String DATUM_ONTBINDING = "550710";
    // Plaats waar huwelijk/partnerschap is ontbonden of nietig verklaard
    private static final String PLAATS_ONTBINDING = "550720";
    // Land waar huwelijk/partnerschap is ontbonden of nietig verklaard
    private static final String LAND_ONTBINDING = "550730";
    // Code opgenomen in Tabel 41: Tabel Reden ontbinding/nietigverklaring huwelijk/partnerschap
    // Zie: https://publicaties.rvig.nl/Landelijke_tabellen
    // Geeft aan om welke reden het parnerschap is ontbonden of nietig verklaard
    private static final String REDEN_ONTBINDING = "550740";

    public GeschiedenisHuwelijkOfPartnerschap(final Map<String, String> values, final Clock clock) {
        super(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP, values, clock);
    }

    public String getBsnPartner() {
        return get(BSN);
    }

    public String getDatumVoltrokken() {
        return get(DATUM_VOLTROKKEN);
    }

    public String getPlaatsVoltrokken() {
        return get(PLAATS_VOLTROKKEN);
    }

    public String getLandVoltrokken() {
        return get(LAND_VOLTROKKEN);
    }

    public String getDatumOntbinding() {
        return get(DATUM_ONTBINDING);
    }

    public String getPlaatsOntbinding() {
        return get(PLAATS_ONTBINDING);
    }

    public String getLandOntbinding() {
        return get(LAND_ONTBINDING);
    }

    public String getRedenOntbinding() {
        return get(REDEN_ONTBINDING);
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
                getRedenOntbinding());
    }
}
