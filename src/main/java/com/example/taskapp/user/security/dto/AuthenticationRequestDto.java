package com.example.taskapp.user.security.dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
