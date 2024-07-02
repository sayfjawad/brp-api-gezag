package nl.rijksoverheid.mev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * Exceptie voor gezag
 */
public abstract class GezagException extends ErrorResponseException {

    protected GezagException(final String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    protected GezagException(final HttpStatusCode httpStatusCode, final String message) {
        super(
            httpStatusCode,
            ProblemDetail.forStatusAndDetail(httpStatusCode, message),
            null
        );
    }
}
