package com.example.taskapp.user.security.controller;

import com.example.taskapp.user.security.dto.RegistrationRequestDto;
import com.example.taskapp.user.security.dto.RegistrationResponseDto;
import com.example.taskapp.user.security.mapper.UserRegistrationMapper;
import com.example.taskapp.user.security.securityservice.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;
    private final UserRegistrationMapper userRegistrationMapper;

    public RegistrationController(UserRegistrationService userRegistrationService, UserRegistrationMapper userRegistrationMapper) {
        this.userRegistrationService = userRegistrationService;
        this.userRegistrationMapper = userRegistrationMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(
            @Valid @RequestBody
            RegistrationRequestDto registrationRequestDto
            ){
        final var registeredUser = userRegistrationService
                .registerUser(registrationRequestDto);
        return ResponseEntity.ok(userRegistrationMapper.toRegistrationResponseDto(registeredUser));
    }
}
