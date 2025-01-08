package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Ouder1 voor persoon
 */
@Categorie(number = "02", name = "ouder 1")
public class Ouder1 extends PotentieelInOnderzoek {

    @VeldNummer(number = "020120", name = "burgerservicenummer van ouder 1")
    private final String burgerservicenummer;
    @VeldNummer(number = "020240", name = "geslachtsnaam van ouder 1")
    private final String geslachtsnaam;
    @VeldNummer(number = "028120", name = "aktenummer van ouder 1")
    private final String aktenummer;
    @VeldNummer(number = "026210", name = "datum ingang familiebetrekking van ouder 1")
    private final String datumIngangFamilieBetrekking;
    @VeldNummer(number = "020410", name = "geslachts aanduiding van persoon")
    private final String geslachtsAanduiding;

    public Ouder1(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        burgerservicenummer = burgerServiceNrAsString;
        geslachtsnaam = lo3PlPersoonRecord.getGeslachtsNaam();
        aktenummer = lo3PlPersoonRecord.getAkteNr();
        datumIngangFamilieBetrekking = Objects.toString(lo3PlPersoonRecord.getFamilieBetrekStartDatum(), null);
        aanduidingGegevensInOnderzoek = onderzoekGegevensAanduidingAsString;
        datumEindeOnderzoek = Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null);
        geslachtsAanduiding = Objects.toString(lo3PlPersoonRecord.getGeslachtsAand(), null);
    }

    public String getBurgerservicenummer() {
        registerIfInOnderzoek("burgerservicenummer", getClass());

        return burgerservicenummer;
    }

    public String getGeslachtsnaam() {
        registerIfInOnderzoek("geslachtsnaam", getClass());

        return geslachtsnaam;
    }

    public String getDatumIngangFamiliebetrekking() {
        registerIfInOnderzoek("datumIngangFamilieBetrekking", getClass());

        return datumIngangFamilieBetrekking;
    }

    public String getAktenummer() {
        registerIfInOnderzoek("aktenummer", getClass());

        return aktenummer;
    }

    public String getGeslachtsAanduiding() {
        registerIfInOnderzoek("geslachtsAanduiding", getClass());

        return geslachtsAanduiding;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getBurgerservicenummer(),
            getGeslachtsnaam(),
            getDatumIngangFamiliebetrekking(),
            getAktenummer(),
            getAanduidingGegevensInOnderzoek(),
            getDatumEindeOnderzoek());
    }
}