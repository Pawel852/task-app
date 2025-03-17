package com.example.taskapp.user.security.controller;

import com.example.taskapp.user.security.dto.AuthenticationRequestDto;
import com.example.taskapp.user.security.dto.AuthenticationResponseDto;
import com.example.taskapp.user.security.securityservice.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

//@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody final AuthenticationRequestDto authenticationRequestDto){

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestDto));
    }


}
