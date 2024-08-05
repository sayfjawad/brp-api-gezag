package nl.rijksoverheid.mev.common.util;

import java.util.Collections;
import java.util.List;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.exception.InvalidBSNException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for bsn validator
 */
class BsnValidatorTest {

    private static final String BSN_EMPTY = "";
    private static final String BSN_INVALID = "999999999";
    private static final String BSN_VALID = "999999564";

    @Test
    void nullBsnExpectingTrue() throws GezagException {
        assertFalse(new BSNValidator().isValid((String)null));
    }

    @Test
    void emptyBsnExpectingFalse() {
        GezagException exception = assertThrows(InvalidBSNException.class,
                () -> new BSNValidator().isValid(BSN_EMPTY));

        assertNotNull(exception);
    }

    @Test
    void invelidBsnExpectingFalse() throws GezagException {
        assertFalse(new BSNValidator().isValid(BSN_INVALID));
    }

    @Test
    void validBsnExpectingTrue() throws GezagException {
        assertTrue(new BSNValidator().isValid(BSN_VALID));
    }

    @Test
    void validBsnsWithoutValue() throws GezagException {
        List<String> bsns = null;

        assertTrue(new BSNValidator().isValid(bsns));
    }

    @Test
    void validBsnsWithEmptyValue() throws GezagException {
        List<String> bsns = Collections.EMPTY_LIST;

        assertTrue(new BSNValidator().isValid(bsns));
    }

    @Test
    void validBsnsWithOneEntryBeingEmpty() throws GezagException {
        List<String> bsns = List.of("");

        GezagException exception = assertThrows(InvalidBSNException.class,
                () -> new BSNValidator().isValid(bsns));

        assertNotNull(exception);
    }

    @Test
    void validBsnsWithOneEntryBeingInvalid() throws GezagException {
        List<String> bsns = List.of(BSN_INVALID);
        
        assertFalse(new BSNValidator().isValid(bsns));
    }

    @Test
    void validBsnsWithOneEntryBeingValid() throws GezagException {
        List<String> bsns = List.of(BSN_VALID);

        assertTrue(new BSNValidator().isValid(bsns));
    }

    @Test
    void validBsnsWithMultipleEntriesOneInvalidOthersValid() throws GezagException {
        List<String> bsns = List.of(BSN_VALID, BSN_INVALID, BSN_VALID);

        assertFalse(new BSNValidator().isValid(bsns));
    }

    @Test
    void validBsnsWithMultipleEntriesAllValid() throws GezagException {
        List<String> bsns = List.of(BSN_VALID, BSN_VALID, BSN_VALID);

        assertTrue(new BSNValidator().isValid(bsns));
    }
}