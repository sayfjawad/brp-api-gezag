package nl.rijksoverheid.mev;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Een class met @ExceptionHandler methodes die alle exceptions van {@link OuderlijkGezagV1ApiController} afhandelen
 * door {@code ErrorResponse}s terug te geven met RFC 7807 geformatteerde error details in de request body.
 */
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
}
