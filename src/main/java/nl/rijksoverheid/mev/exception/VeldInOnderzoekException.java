package nl.rijksoverheid.mev.exception;

/**
 * Exceptie wanneer een veld in onderzoek is
 */
public class VeldInOnderzoekException extends GezagException {

    public VeldInOnderzoekException(final String message) {
        super(message);
    }

}
