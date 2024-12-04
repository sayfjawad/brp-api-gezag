package nl.rijksoverheid.mev.gezagsmodule.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.Clock;
import java.util.Map;
import java.util.Objects;

/**
 * Geschiedenis huwelijk of partnerschap
 */
public class GeschiedenisHuwelijkOfPartnerschap extends PotentieelInOnderzoek {

    private static final String BSN = "550120";
    // Datum waarop het huwelijk is voltrokken of het partnerschap is aangegaan. yyyyMMdd formaat
    private static final String DATUM_VOLTROKKEN = "550610";
    // Datum waarop het huwelijk/partnerschap is ontbonden of nietig verklaard. yyyyMMdd formaat
    private static final String DATUM_ONTBINDING = "550710";
    // Code opgenomen in Tabel 41: Tabel Reden ontbinding/nietigverklaring huwelijk/partnerschap
    // Zie: https://publicaties.rvig.nl/Landelijke_tabellen
    // Geeft aan om welke reden het parnerschap is ontbonden of nietig verklaard
    private static final String REDEN_ONTBINDING = "550740";

    public GeschiedenisHuwelijkOfPartnerschap(final Map<String, String> values) {
        super(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP, values);
    }

    public String getBsnPartner() {
        return get(BSN, "burgerservicenummer partner van relatie (historie)");
    }

    public String getDatumVoltrokken() {
        return get(DATUM_VOLTROKKEN, "datum voltrokken van relatie (historie)");
    }

    public String getDatumOntbinding() {
        return get(DATUM_ONTBINDING, "datum ontbinding van relatie (historie)");
    }

    public String getRedenOntbinding() {
        return get(REDEN_ONTBINDING, "reden ontbinding van relatie (historie)");
    }

    @Override
    public String getCategorieName() {
        return "relatie (historie)";
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
                getDatumOntbinding(),
                getRedenOntbinding());
    }
}