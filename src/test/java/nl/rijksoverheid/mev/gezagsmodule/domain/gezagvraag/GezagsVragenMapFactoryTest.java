package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GezagsVragenMapFactoryTest {

    @Test
    @DisplayName("Given two mock GezagVraagFunctions with distinct question IDs " +
        "when getGezagVraagFunctionsMap is called " +
        "then the returned map has the expected entries and correct mapping.")
    void testGetGezagVraagFunctionsMap() {
        final var mockFunction1 = mock(GezagVraag.class);
        when(mockFunction1.getQuestionId()).thenReturn("v1.1");
        GezagVraag mockFunction2 = mock(GezagVraag.class);
        when(mockFunction2.getQuestionId()).thenReturn("v2.1");

        final var factory = new GezagsVragenMapFactory(List.of(mockFunction1, mockFunction2));
        final var resultMap = factory.getGezagVragen();

        assertEquals(2, resultMap.size(), "The map should contain two entries");
        assertSame(mockFunction1, resultMap.get("v1.1"), "Expected mockFunction1 under key 'v1.1'");
        assertSame(mockFunction2, resultMap.get("v2.1"), "Expected mockFunction2 under key 'v2.1'");
    }
}
