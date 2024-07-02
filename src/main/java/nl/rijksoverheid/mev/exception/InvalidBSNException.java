package nl.rijksoverheid.mev.exception;

import org.springframework.http.HttpStatus;

/**
 * Exceptie wanneer een invalide BSN is ontvangen
 */
public class InvalidBSNException extends GezagException {

    public InvalidBSNException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Burgerservicenummer voldoet niet aan de elfproef");
    }
}
