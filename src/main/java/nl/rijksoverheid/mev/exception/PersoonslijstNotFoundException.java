package nl.rijksoverheid.mev.exception;

public class PersoonslijstNotFoundException extends RuntimeException {

    public PersoonslijstNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
