package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Kind zoals opgenomen voor persoon in de persoonslijst
 */
@Categorie(number = "09", name = "kind")
public class Kind extends PotentieelInOnderzoek {

    @VeldNummer(number = "090120", name = "burgerservicenummer van kind")
    private final String burgerservicenummer;
    @VeldNummer(number = "090310", name = "geboortedatum van kind")
    private final String geboortedatum;

    private static final int MEERDERJARIGE_LEEFTIJD = 180000;

    public Kind(final Lo3PlPersoonRecord lo3PlPersoonRecord) {
        var burgerServiceNr = lo3PlPersoonRecord.getBurgerServiceNr();
        var burgerServiceNrAsString = burgerServiceNr == null ? null : "%09d".formatted(burgerServiceNr);

        var onderzoekGegevensAanduiding = lo3PlPersoonRecord.getOnderzoekGegevensAand();
        var onderzoekGegevensAanduidingAsString = onderzoekGegevensAanduiding == null ? null : "%06d".formatted(onderzoekGegevensAanduiding);

        burgerservicenummer = burgerServiceNrAsString;
        geboortedatum = Objects.toString(lo3PlPersoonRecord.getGeboorteDatum(), null);
        aanduidingGegevensInOnderzoek = onderzoekGegevensAanduidingAsString;
        datumEindeOnderzoek = Objects.toString(lo3PlPersoonRecord.getOnderzoekEindDatum(), null);
    }

    public String getBurgerservicenummer() {
        registerIfInOnderzoek("burgerservicenummer", getClass());

        return burgerservicenummer;
    }

    public String getGeboortedatum() {
        registerIfInOnderzoek("geboortedatum", getClass());

        return geboortedatum;
    }

    public boolean isMinderjarig() {
        if (geboortedatum == null) {
            return false;
        }
        int minderjarigTotInt = Integer.parseInt(geboortedatum) + MEERDERJARIGE_LEEFTIJD;
        int datumVandaag = Integer.parseInt(LocalDate.now().format(FORMATTER));
        return minderjarigTotInt > datumVandaag;
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
            getAanduidingGegevensInOnderzoek(),
            getDatumEindeOnderzoek());
    }
}