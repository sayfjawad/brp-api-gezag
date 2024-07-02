package nl.rijksoverheid.mev.common.util;

import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
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
        assertFalse(new BSNValidator().isValid(null));
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
}
