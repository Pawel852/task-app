package com.example.TaskApp.User.Security.Controller;

import com.example.TaskApp.User.Security.Dto.AuthenticationRequestDto;
import com.example.TaskApp.User.Security.Dto.AuthenticationResponseDto;
import com.example.TaskApp.User.Security.SecurityService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
