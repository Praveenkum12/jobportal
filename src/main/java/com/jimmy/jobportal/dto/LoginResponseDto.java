package com.jimmy.jobportal.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}