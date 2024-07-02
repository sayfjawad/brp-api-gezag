package nl.rijksoverheid.mev.exception;

/**
 * Exceptie wanneer SSL context niet gemaakt kan worden
 */
public class SSLContextCreationException extends GezagException {

    public SSLContextCreationException(final Throwable cause) {
        super(cause.getMessage());
    }
}
