package nl.rijksoverheid.mev.gezagsmodule.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VragenlijstServiceTest {

    private VragenlijstService classUnderTest;

    @Test
    void getVragenMapResultsInReadVragen() {
        classUnderTest = new VragenlijstService();

        Map<String, Map<String, String>> questions = classUnderTest.getVragenMap();

        assertFalse(questions.isEmpty());
        for(Map.Entry<String, Map<String, String>> questionSet : questions.entrySet()) {
            String key = questionSet.getKey();
            assertTrue(key != null && !key.isEmpty());
            Map<String, String> answerSet = questionSet.getValue();
            assertFalse( answerSet.isEmpty());
            Map.Entry<String, String> firstAnswerEntry = answerSet.entrySet().stream().findFirst().get();
            String firstEntryKey = firstAnswerEntry.getKey();
            String firstEntryValue = firstAnswerEntry.getKey();
            assertTrue(firstEntryKey != null && !firstEntryKey.isEmpty());
            assertTrue(firstEntryValue != null && !firstEntryValue.isEmpty());
        }
    }
}