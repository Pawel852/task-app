package com.example.TaskApp.User.Security.Dto;


public record RegistrationRequestDto(
        String username,
        String email,
        String password

) {
}
