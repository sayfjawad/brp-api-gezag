package nl.rijksoverheid.mev.gezagsmodule.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeeftijdTest {

    private static final String ONBEKENDE_GEBOORTEDATUM_BRP = "00000000";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void leeftijdZonderGeboortedatum() {
        String geboorteDatum = null;

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdMetLegeGeboortedatum() {
        String geboorteDatum = "";

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdMetOnbekendeGeboortedatum() {
        String geboorteDatum = ONBEKENDE_GEBOORTEDATUM_BRP;

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonDieGisterenGeborenIs() {
        String geboorteDatum = getDateFor(0, 0, 1);

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonDieMorgenMeerderjarigWordt() {
        String geboorteDatum = getDateFor(17, 11, 30);

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonDieVandaagMeerderjarigIsGeworden() {
        String geboorteDatum = getDateFor(18, 0, 0);
        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonDieGisterenMeerderjarigIsGeworden() {
        String geboorteDatum = getDateFor(18, 0, 1);

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorMinderjarigPersoonMetOnbekendeDag() {
        String geboorteDatum = makeDayUnknown(getDateFor(6, 1, 4));

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorMinderjarigPersoonMetOnbekendeMaand() {
        String geboorteDatum = makeMonthUnknown(getDateFor(6, 1, 4));

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorMinderjarigPersoonMetOnbekendeDagEnMaand() {
        String geboorteDatum = makeDayUnknown(getDateFor(6, 1, 4));
        geboorteDatum = makeMonthUnknown(geboorteDatum);

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonMetOnbekendeDagEnMaandDieDitJaarMeerderjarigWordt() {
        String geboorteDatum = makeDayUnknown(getDateFor(17, 1, 4));
        geboorteDatum = makeMonthUnknown(geboorteDatum);

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertFalse(leeftijd.isMeerderjarig());
        assertTrue(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonMetOnbekendeDagDieDezeMaandMeerderjarigWordt() {
        String geboorteDatum = makeDayUnknown(getDateFor(18, 0, 0));

        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    @Test
    public void leeftijdVoorPersoonMetOnbekendeDagDieDezeMaandMeerderjarigIsGeworden() {
        String geboorteDatum = makeDayUnknown(getDateFor(18, 1, 0));
        Leeftijd leeftijd = Leeftijd.of(geboorteDatum);

        assertTrue(leeftijd.isMeerderjarig());
        assertFalse(leeftijd.isMinderjarig());
    }

    private String getDateFor(final int yearsAgo, final int monthsAgo, final int daysAgo) {
        return LocalDate.now()
            .minusYears(yearsAgo)
            .minusMonths(monthsAgo)
            .minusDays(daysAgo).format(FORMATTER);
    }

    private String makeDayUnknown(final String geboorteDatum) {
        return geboorteDatum.substring(0,6) + "00";
    }

    private String makeMonthUnknown(final String geboorteDatum) {
        return geboorteDatum.substring(0,4) + "00" + geboorteDatum.substring(6,8);
    }
}