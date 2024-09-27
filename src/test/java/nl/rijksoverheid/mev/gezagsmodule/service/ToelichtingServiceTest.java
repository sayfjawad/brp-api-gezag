package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToelichtingServiceTest {

    private ToelichtingService classUnderTest;

    private static final String BASE_TOELICHTING = "mock";
    private static final String BASE_MISSENDE_VELDEN_TOELICHTING = "missende gegevens: %s";
    private static final String EXPECTED = "Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. %s";
    private static final String FIELD_1 = "field 1";
    private static final String FIELD_2 = "field 2";

    @BeforeEach
    public void setup() {
        classUnderTest = new ToelichtingService();
    }

    @Test
    void nullBaseToelichtingVeldenInOnderzoekAndMissendeGegevensExpectingEmptyString() {
        String toelichting = null;
        VeldenInOnderzoek veldenInOnderzoek = null;
        List<String> missendeGegevens = null;

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);

        assertTrue(result.isEmpty());
    }

    @Test
    void emptyBaseToelichtingEnGeenVeldenInOnderzoekAndMissendeGegevensExpectingEmptyString() {
        String toelichting = "";
        VeldenInOnderzoek veldenInOnderzoek = null;
        List<String> missendeGegevens = null;

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);

        assertTrue(result.isEmpty());
    }

    @Test
    void baseToelichtingAndGeenVeldenInOnderzoekAndMissendeGegevensExpectingEmpty() {
        String toelichting = BASE_TOELICHTING;
        VeldenInOnderzoek veldenInOnderzoek = null;
        List<String> missendeGegevens = null;

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);

        assertTrue(result.isEmpty());
    }

    @Test
    void baseToelichtingAndEmptyVeldenInOnderzoekAndMissendeGegevensExpectingEmpty() {
        String toelichting = BASE_TOELICHTING;
        VeldenInOnderzoek veldenInOnderzoek = new VeldenInOnderzoek();
        List<String> missendeGegevens = null;

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);

        assertTrue(result.isEmpty());
    }

    @Test
    void baseToelichtingAndEmptyVeldenInOnderzoekAndEmptyMissendeGegevensExpectingEmpty() {
        String toelichting = BASE_TOELICHTING;
        VeldenInOnderzoek veldenInOnderzoek = new VeldenInOnderzoek();
        List<String> missendeGegevens = new ArrayList<>();

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);

        assertTrue(result.isEmpty());
    }

    @Test
    void baseToelichtingAndOneVeldenInOnderzoekForPersoonExpectingVeldInOnderzoek() {
        var expected = EXPECTED.formatted("Persoonslijst van persoon: field 1.");

        VeldenInOnderzoek veldenInOnderzoek = new VeldenInOnderzoek();
        veldenInOnderzoek.setPersoon(List.of(FIELD_1));
        List<String> missendeGegevens = new ArrayList<>();

        String result = classUnderTest.decorateToelichting(BASE_TOELICHTING, veldenInOnderzoek, missendeGegevens);

        assertEquals(expected, result);
    }

    @Test
    void baseToelichtingAndTwoVeldenInOnderzoekOnePersoonAndOneOuder1ExpectingVeldenInOnderzoek() {
        var expected = EXPECTED.formatted("Persoonslijst van persoon: field 1. Persoonslijst van ouder 1: field 1.");

        VeldenInOnderzoek veldenInOnderzoek = new VeldenInOnderzoek();
        veldenInOnderzoek.setPersoon(List.of(FIELD_1));
        veldenInOnderzoek.setOuder1(List.of(FIELD_1));
        List<String> missendeGegevens = new ArrayList<>();

        String result = classUnderTest.decorateToelichting(BASE_TOELICHTING, veldenInOnderzoek, missendeGegevens);

        assertEquals(expected, result);
    }
    
    @Test
    void baseToelichtingAndOneMissendeGegegevensExpectingFormattedToelichting() {
        String toelichting = BASE_MISSENDE_VELDEN_TOELICHTING;
        VeldenInOnderzoek veldenInOnderzoek = null;
        List<String> missendeGegevens = List.of(FIELD_1);

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);
        
        assertTrue(result.startsWith(toelichting.substring(0, (toelichting.length() - 2))));
        assertTrue(result.contains(FIELD_1));
    }
    
        
    @Test
    void baseToelichtingAndTwoMissendeGegegevensExpectingFormattedToelichting() {
        String toelichting = BASE_MISSENDE_VELDEN_TOELICHTING;
        VeldenInOnderzoek veldenInOnderzoek = null;
        List<String> missendeGegevens = List.of(FIELD_1, FIELD_2);

        String result = classUnderTest.decorateToelichting(toelichting, veldenInOnderzoek, missendeGegevens);
        
        assertTrue(result.startsWith(toelichting.substring(0, (toelichting.length() - 2))));
        assertTrue(result.contains(FIELD_1));
        assertTrue(result.contains(FIELD_2));
    }
    
    @Test
    void baseToelichtingAndTwoVeldenInOnderzoekAndTwoMissendeGegevensExpectingVeldenInOnderzoek() {
        var expected = EXPECTED.formatted("Persoonslijst van persoon: field 1. Persoonslijst van ouder 1: field 1.");

        VeldenInOnderzoek veldenInOnderzoek = new VeldenInOnderzoek();
        veldenInOnderzoek.setPersoon(List.of(FIELD_1));
        veldenInOnderzoek.setOuder1(List.of(FIELD_1));
        List<String> missendeGegevens = List.of(FIELD_1, FIELD_2);

        String result = classUnderTest.decorateToelichting(BASE_TOELICHTING, veldenInOnderzoek, missendeGegevens);

        assertEquals(expected, result);
    }
}
