package br.com.jobson.email_service.infra;

import br.com.jobson.email_service.exceptions.EmailServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailServiceException.class)
    private ResponseEntity<DefaultResponseError> emailServiceHandler(EmailServiceException exception) {
        DefaultResponseError defaultResponse = new DefaultResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(defaultResponse);
    }
}
