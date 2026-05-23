package com.jimmy.jobportal.dto;

import com.jimmy.jobportal.contact.service.IContactService;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime timestamp) {
}
