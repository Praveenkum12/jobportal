package com.jimmy.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactRequestDto(
        @NotBlank(message = "Name can't be empty")
        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        String name,

        @Email(message = "Invalid Email Id")
        @NotBlank(message = "Email can't be empty")
        String email,

        @NotBlank(message = "Message can't be empty")
        @Size(min = 5, max = 500, message = "Message must be between 5 and 500 characters")
        String message,

        @NotBlank(message = "Subject can't be empty")
        @Size(min = 5, max = 150, message = "Subject must be between 5 and 150 characters")
        String subject,

        @NotBlank(message = "UserType can't be empty")
        @Pattern(regexp = "Job Seeker|Employer|Other", message = "UserType must be one of: Job Seeker, Employer, Other")
        String userType) {
}
