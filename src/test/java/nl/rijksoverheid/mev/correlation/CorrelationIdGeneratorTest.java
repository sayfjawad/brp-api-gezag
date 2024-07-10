package nl.rijksoverheid.mev.correlation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for correlation id generator
 */
@ExtendWith(MockitoExtension.class)
class CorrelationIdGeneratorTest {

    @InjectMocks
    private CorrelationIdGenerator classUnderTest;

    private static final String DEFAULT_DIENSTVERLENER_ID = "00000000000000000000";
    private static final String DIENSTVERLENER_ID = "00000003301977740000";
    private static final int EXPECTED_LENGTH = 61;

    @Test
    void generateCorrelationIdWithoutDienstverlenerIdExpectingToContainPlaceholder() {
        String dienstverlenerId = null;

        String correlationId = classUnderTest.generate(dienstverlenerId);

        assertEquals(EXPECTED_LENGTH, correlationId.length());
        assertTrue(correlationId.contains(DEFAULT_DIENSTVERLENER_ID));
    }

    @Test
    void generateCorrelationIdWithEmptyDienstverlenerIdExpectingToContainPlaceholder() {
        String dienstverlenerId = "";

        String correlationId = classUnderTest.generate(dienstverlenerId);

        assertEquals(EXPECTED_LENGTH, correlationId.length());
        assertTrue(correlationId.contains(DEFAULT_DIENSTVERLENER_ID));
    }

    @Test
    void generateCorrelationIdWithDienstverlenerIdExpectingToContainOin() {
        String dienstverlenerId = DIENSTVERLENER_ID;

        String correlationId = classUnderTest.generate(dienstverlenerId);

        assertEquals(EXPECTED_LENGTH, correlationId.length());
        assertTrue(correlationId.contains(DIENSTVERLENER_ID));
    }
}
