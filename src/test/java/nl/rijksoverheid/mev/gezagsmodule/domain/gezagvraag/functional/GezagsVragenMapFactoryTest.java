package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GezagsVragenMapFactoryTest {

    @Test
    @DisplayName("Given two mock GezagVraagFunctions with distinct question IDs " +
            "when getGezagVraagFunctionsMap is called " +
            "then the returned map has the expected entries and correct mapping.")
    void testGetGezagVraagFunctionsMap() {
        // GIVEN two mock GezagVraagFunction objects with unique question IDs
        final var mockFunction1 = mock(GezagVraagFunction.class);
        when(mockFunction1.getQuestionId()).thenReturn("v1.1");
        GezagVraagFunction mockFunction2 = mock(GezagVraagFunction.class);
        when(mockFunction2.getQuestionId()).thenReturn("v2.1");
        // and a GezagsVragenMapFactory created with these mocks
        final var factory = new GezagsVragenMapFactory(List.of(mockFunction1, mockFunction2));
        // WHEN we call getGezagVraagFunctionsMap
        final var resultMap = factory.getGezagVraagFunctionsMap();
        // THEN the map size matches the number of mocks,
        // and each key corresponds to its respective GezagVraagFunction.
        assertEquals(2, resultMap.size(), "The map should contain two entries");
        assertSame(mockFunction1, resultMap.get("v1.1"), "Expected mockFunction1 under key 'v1.1'");
        assertSame(mockFunction2, resultMap.get("v2.1"), "Expected mockFunction2 under key 'v2.1'");
    }
}
