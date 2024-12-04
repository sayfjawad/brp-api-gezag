package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Ouder2 voor persoon
 */
@Categorie(number = "03", name = "ouder 2")
public class Ouder2 extends PotentieelInOnderzoek {

    @VeldNummer(number = "030120", name = "burgerservicenummer van ouder 2")
    private final String burgerservicenummer;

    @VeldNummer(number = "030240", name = "geslachtsnaam van ouder 2")
    private final String geslachtsnaam;
    @VeldNummer(number = "038120", name = "aktenummer van ouder 2")
    private final String aktenummer;
    @VeldNummer(number = "036210", name = "datum ingang familiebetrekking van ouder 2")
    private final String datumIngangFamilieBetrekking;

    public Ouder2(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
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