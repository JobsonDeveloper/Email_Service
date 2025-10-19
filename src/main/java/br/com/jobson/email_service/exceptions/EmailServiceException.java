package br.com.jobson.email_service.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException() {
        super("Error while sending email");
    }

    public EmailServiceException(String message, Throwable exception) {
        super(message, exception);
    }
}
