package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * Persoon (vanuit persoonslijst zoals opgenomen in de BRP)
 */
@Categorie(number = "01", name = "persoon")
public class Persoon extends PotentieelInOnderzoek implements WithAktenummer {

    @VeldNummer(number = "010120", name = "burgerservicenummer van persoon")
    private final String burgerservicenummer;
    @VeldNummer(number = "010240", name = "geslachtsnaam van ouder 2")
    private final String geslachtsnaam;
    @VeldNummer(number = "010310", name = "geboortedatum van persoon")
    private final String geboortedatum;
    @VeldNummer(number = "010330", name = "geboorteland van persoon")
    private final String geboorteland;
    @VeldNummer(number = "018120", name = "aktenummer van persoon")
    private final String aktenummer;
    @VeldNummer(number = "010410", name = "geslachts aanduiding van persoon")
    private final String geslachtsAanduiding;

    public Persoon(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        burgerservicenummer = burgerServiceNrAsString;
        geslachtsnaam = lo3PlPersoonRecord.getGeslachtsNaam();
        geboortedatum = Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null);
        geboorteland = Objects.toString(lo3PlPersoonRecord.getGeboorteLandCode(), null);
        aktenummer = lo3PlPersoonRecord.getAkteNr();
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

    public String getGeboortedatum() {
        registerIfInOnderzoek("geboortedatum", getClass());

        return geboortedatum;
    }

    public String getGeboorteland() {
        registerIfInOnderzoek("geboorteland", getClass());

        return geboorteland;
    }

    @Override
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
            getGeboortedatum(),
            getGeboorteland(),
            getAktenummer(),
            getAanduidingGegevensInOnderzoek(),
            getDatumEindeOnderzoek());
    }
}