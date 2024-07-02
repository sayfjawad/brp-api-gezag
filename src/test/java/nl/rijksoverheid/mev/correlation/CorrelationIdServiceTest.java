package nl.rijksoverheid.mev.correlation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for correlation id service
 */
@ExtendWith(MockitoExtension.class)
class CorrelationIdServiceTest {

    private CorrelationIdService classUnderTest;

    private static final String CORRELATION_ID = "00-b1d2a5ec5ae1edfe18cd0669c26c5ff6-00000003301977740000-00";
    private static final String DEFAULT_DIENSTVERLENER_ID = "00000000000000000000";
    private static final String DIENSTVERLENER_ID = "00000004132080135000";
    private static final String COMPONENT_ID = "mock";

    @BeforeEach
    void setup() {
        classUnderTest = new CorrelationIdService(new CorrelationIdGenerator());
    }

    @Test
    void prepareForProcessWithoutCorrelationIdAndDienstverlenerIdExpectingNewIdToBeCreated() {
        String correlationId = null;
        String dienstverlenerId = null;

        classUnderTest.prepareForProcess(correlationId, dienstverlenerId);
        String result = classUnderTest.getCorrelationIdForProcess();

        assertNotNull(result);
        assertTrue(result.contains(DEFAULT_DIENSTVERLENER_ID));
    }

    @Test
    void prepareForProcessWithEmptyCorrelationIdExpectingNewIdToBeCreated() {
        String correlationId = "";
        String dienstverlenerId = null;
        
        classUnderTest.prepareForProcess(correlationId, dienstverlenerId);
        String result = classUnderTest.getCorrelationIdForProcess();

        assertNotNull(result);
        assertTrue(result.contains(DEFAULT_DIENSTVERLENER_ID));
    }

    @Test
    void prepareForProcessWithoutCorrelationIdAndHavingDienstverlenerIdExpectingNewIdToBeCreated() {
        String correlationId = null;
        String dienstverlenerId = DIENSTVERLENER_ID;

        classUnderTest.prepareForProcess(correlationId, dienstverlenerId);
        String result = classUnderTest.getCorrelationIdForProcess();

        assertNotNull(result);
        assertTrue(result.contains(dienstverlenerId));
    }

    @Test
    void prepareForProcessWithCorrelationIdAndNoDienstverlenerIdExpectingIdToBeUsed() {
        String correlationId = CORRELATION_ID;
        String dienstverlenerId = null;

        classUnderTest.prepareForProcess(correlationId, dienstverlenerId);
        String result = classUnderTest.getCorrelationIdForProcess();

        assertNotNull(result);
        assertEquals(CORRELATION_ID, result);
        assertFalse(result.contains(DEFAULT_DIENSTVERLENER_ID));
    }

    @Test
    void prepareForProcessWithCorrelationIdAndDienstverlenerIdExpectingIdToBeUsed() {
        String correlationId = CORRELATION_ID;
        String dienstverlenerId = DIENSTVERLENER_ID;

        classUnderTest.prepareForProcess(correlationId, dienstverlenerId);
        String result = classUnderTest.getCorrelationIdForProcess();

        assertNotNull(result);
        assertEquals(CORRELATION_ID, result);
        assertFalse(result.contains(DEFAULT_DIENSTVERLENER_ID));
    }
}
