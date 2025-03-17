package com.example.taskapp.user.security.dto;


public record RegistrationRequestDto(
        String username,
        String email,
        String password

) {
}
