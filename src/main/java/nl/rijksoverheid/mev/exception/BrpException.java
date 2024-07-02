package nl.rijksoverheid.mev.exception;

import org.springframework.http.HttpStatus;

/**
 * Exceptie wanneer BRP functionaliteit mis gaat
 */
public class BrpException extends GezagException {
    
    private static final String BRP_SERVICE_NOT_REACHABLE = "Gegevensbron niet beschikbaar";
    
    public BrpException() {
        super(HttpStatus.SERVICE_UNAVAILABLE, BRP_SERVICE_NOT_REACHABLE);
    }
    
    public BrpException(final String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }
}
