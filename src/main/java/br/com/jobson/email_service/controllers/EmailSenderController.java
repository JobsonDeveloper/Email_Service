package br.com.jobson.email_service.controllers;

import br.com.jobson.email_service.application.EmailSenderService;
import br.com.jobson.email_service.core.dto.EmailRequest;
import br.com.jobson.email_service.core.dto.EmailSentDto;
import br.com.jobson.email_service.exceptions.EmailServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Email", description = "Email-related operations")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailService) {
        this.emailSenderService = emailService;
    }

    @Operation(
            summary = "Sending Email",
            description = "Send a message via email",
            tags = {"Email"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Email sent successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmailSentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while sending email"
                    )
            }
    )
    @PostMapping("/api/email/send")
    public ResponseEntity<EmailSentDto> sendEmail(@RequestBody EmailRequest request) {
        try {
            this.emailSenderService.sendEmail(
                    request.to(),
                    request.subject(),
                    request.body()
            );

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmailSentDto("Email sent successfully"));
        } catch (EmailServiceException exception) {
            throw new EmailServiceException();
        }
    }
}
