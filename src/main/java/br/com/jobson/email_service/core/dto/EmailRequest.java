package br.com.jobson.email_service.core.dto;

public record EmailRequest(String to, String subject, String body) {
}
