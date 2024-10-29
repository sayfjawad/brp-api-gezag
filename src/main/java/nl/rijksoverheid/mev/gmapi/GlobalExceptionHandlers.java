package nl.rijksoverheid.mev.gmapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlers.class);

    @ExceptionHandler
    public ErrorResponse handle(RuntimeException ex) {
        log.error("catching", ex);
        return new ServerErrorException(ex.getMessage(), ex);
    }
}
