package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Domein object voor leeftijd binnen de gezagsmodule
 */
public class Leeftijd {

    private final int geboortedatum;
    private final int datumVolwassenVanaf;

    private static final int MEERDERJARIGE_LEEFTIJD = 180000;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Leeftijd of(String geboortedatumAsString) {
        return new Leeftijd(geboortedatumAsString);
    }

    private Leeftijd(String geboortedatumAsString) {
        geboortedatumAsString = (geboortedatumAsString == null || geboortedatumAsString.isEmpty() ? "0" : geboortedatumAsString);
        geboortedatum = Integer.parseInt(geboortedatumAsString);
        datumVolwassenVanaf = Integer.parseInt(LocalDate.now().format(FORMATTER)) - MEERDERJARIGE_LEEFTIJD;
    }

    /**
     * Meerderjarig is iemand waarvan de leeftijd hoger is dan 17 jaar
     * Ook wanneer de geboortedatum volledig onbekend is, wordt de persoon gezien als meerderjarig
     *
     * @return of de persoon meerderjarig is
     */
    public boolean isMeerderjarig() {
        return geboortedatum == 0 || geboortedatum <= datumVolwassenVanaf;
    }

    /**
     * Wanneer iemand jonger is dan 18 jaar is de persoon minderjarig
     *
     * @return of de persoon minderjarig is
     */
    public boolean isMinderjarig() {
        return geboortedatum > datumVolwassenVanaf;
    }
}
