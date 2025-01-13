package nl.rijksoverheid.mev.gezagsmodule.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AktenummerAfleidingTest {

    private static final String AKTE_ERKENNING = "1AC0109";
    private static final String AKTE_ONTKENNING = "1AE0109";
    private static final String AKTE_ONGEBOREN_VRUCHT = "1AA0109";
    private static final String AKTE_ADOPTIE = "1AQ0109";

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithoutCurrentOrHistory() {
        WithAktenummer current = null;
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithEmptyCurrentAndNoHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithEmptyCurrentAndEmptyHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithCorrectAktenummerInCurrent() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithCorrectAktenummerInHistory() {
        WithAktenummer current = null;
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ERKENNING));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithCorrectAktenummerInCurrentAndHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ERKENNING));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithIncorrectAktenummerInCurrentAndCorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ADOPTIE);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ERKENNING));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithCorrectAktenummerInCurrentAndIncorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkendOfGerechtelijkeVaststelling(current, history);

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithoutValues() {
        WithAktenummer current = null;
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertFalse(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithEmptyCurrentAndNoHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertFalse(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithEmptyCurrentAndEmptyHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertFalse(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderVaststellingWithCorrectAktenummerInCurrent() {
        WithAktenummer current = new AktenummerTest(AKTE_ONTKENNING);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithCorrectAktenummerInHistory() {
        WithAktenummer current = null;
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONTKENNING));

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithCorrectAktenummerInCurrentAndHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ONTKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONTKENNING));

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithIncorrectAktenummerInCurrentAndCorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONTKENNING));

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuderWithCorrectAktenummerInCurrentAndIncorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ONTKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.ontkenningOuderschapDoorOuder(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithoutValues() {
        WithAktenummer current = null;
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendWithEmptyCurrentAndNoHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendWithEmptyCurrentAndEmptyHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendWithCorrectAktenummerInCurrent() {
        WithAktenummer current = new AktenummerTest(AKTE_ONGEBOREN_VRUCHT);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithCorrectAktenummerInHistory() {
        WithAktenummer current = null;
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONGEBOREN_VRUCHT));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithCorrectAktenummerInCurrentAndHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ONGEBOREN_VRUCHT);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONGEBOREN_VRUCHT));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithIncorrectAktenummerInCurrentAndCorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ONGEBOREN_VRUCHT));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithCorrectAktenummerInCurrentAndIncorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ONGEBOREN_VRUCHT);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.ongeborenVruchtErkend(current, history);

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithoutValues() {
        WithAktenummer current = null;
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertFalse(result);
    }

    @Test
    void geadopteerdMetNlAkteWithEmptyCurrentAndNoHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = null;

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertFalse(result);
    }

    @Test
    void geadopteerdMetNlAkteWithEmptyCurrentAndEmptyHistory() {
        WithAktenummer current = new AktenummerTest(null);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertFalse(result);
    }

    @Test
    void geadopteerdMetNlAkteWithCorrectAktenummerInCurrent() {
        WithAktenummer current = new AktenummerTest(AKTE_ADOPTIE);
        List<WithAktenummer> history = new ArrayList<>();

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithCorrectAktenummerInHistory() {
        WithAktenummer current = null;
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithCorrectAktenummerInCurrentAndHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ADOPTIE);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithIncorrectAktenummerInCurrentAndCorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ERKENNING);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ADOPTIE));

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithCorrectAktenummerInCurrentAndIncorrectInHistory() {
        WithAktenummer current = new AktenummerTest(AKTE_ADOPTIE);
        List<WithAktenummer> history = List.of(new AktenummerTest(AKTE_ERKENNING));

        boolean result = AktenummerAfleiding.geadopteerdMetNlAkte(current, history);

        assertTrue(result);
    }

    private class AktenummerTest implements WithAktenummer {

        private final String aktenummer;

        public AktenummerTest(final String aktenummer) {
            this.aktenummer = aktenummer;
        }

        @Override
        public String getAktenummer() {
            return aktenummer;
        }
    }
}