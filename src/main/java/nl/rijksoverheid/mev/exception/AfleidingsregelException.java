package nl.rijksoverheid.mev.exception;

/**
 * Exceptie wanneer iets misgaat met afleidingsregels
 */
public class AfleidingsregelException extends GezagException {

    public AfleidingsregelException(final String message) {
        super(message);
    }
}
