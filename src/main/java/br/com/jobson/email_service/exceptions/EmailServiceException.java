package br.com.jobson.email_service.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message) {
        super(message);
    }

    public EmailServiceException(String message, Throwable exception) {
        super(message, exception);
    }
}
