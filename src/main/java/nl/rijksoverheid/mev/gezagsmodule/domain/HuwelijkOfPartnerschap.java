package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Huwelijk of partnerschap
 */
@Categorie(number = "05", name = "relatie")
public class HuwelijkOfPartnerschap extends PotentieelInOnderzoek {

    @VeldNummer(number = "050120", name = "burgerservicenummer partner van relatie")
    private final String burgerservicenummer;
    /**
     * Datum waarop het huwelijk is voltrokken of het partnerschap is aangegaan.
     * <p>
     * yyyyMMdd formaat
     */
    @VeldNummer(number = "050610", name = "datum voltrokken van relatie")
    private final String datumVoltrokken;
    /**
     * Datum waarop het huwelijk/partnerschap is ontbonden of nietig verklaard.
     * <p>
     * yyyyMMdd formaat
     */
    @VeldNummer(number = "050710", name = "datum ontbinding van relatie")
    private final String datumOntbinding;
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
    @VeldNummer(number = "050740", name = "reden ontbinding van relatie")
    private final String redenOntbinding;

    public HuwelijkOfPartnerschap(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        burgerservicenummer = burgerServiceNrAsString;
        datumVoltrokken = Objects.toString(lo3PlPersoonRecord.getRelatieStartDatum(), null);
        datumOntbinding = Objects.toString(lo3PlPersoonRecord.getRelatieEindDatum(), null);
        redenOntbinding = lo3PlPersoonRecord.getRelatieEindReden();
        aanduidingGegevensInOnderzoek = onderzoekGegevensAanduidingAsString;
        datumEindeOnderzoek = Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null);
    }

    public String getBsnPartner() {
        registerIfInOnderzoek("burgerservicenummer", getClass());

        return burgerservicenummer;
    }

    public String getDatumVoltrokken() {
        registerIfInOnderzoek("datumVoltrokken", getClass());

        return datumVoltrokken;
    }

    public String getDatumOntbinding() {
        registerIfInOnderzoek("datumOntbinding", getClass());

        return datumOntbinding;
    }

    public String getRedenOntbinding() {
        registerIfInOnderzoek("redenOntbinding", getClass());

        return redenOntbinding;
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
            getRedenOntbinding(),
            getAanduidingGegevensInOnderzoek(),
            getDatumEindeOnderzoek());
    }
}