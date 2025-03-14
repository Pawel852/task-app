package com.example.TaskApp.User.Security.Dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
