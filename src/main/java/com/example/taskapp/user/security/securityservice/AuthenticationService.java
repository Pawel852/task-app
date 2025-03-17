package com.example.taskapp.user.security.securityservice;

import com.example.taskapp.user.security.dto.AuthenticationRequestDto;
import com.example.taskapp.user.security.dto.AuthenticationResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

//    public AuthenticationResponseDto authenticate(final AuthenticationRequestDto requestDto){
//        final var authToken = UsernamePasswordAuthenticationToken
//                .unauthenticated(requestDto.username(), requestDto.password());
//        final var auth = authenticationManager.authenticate(authToken);
//
//        final var token = jwtService.generateToken(requestDto.username());
//        return new AuthenticationResponseDto(token);
//
//    }




    public AuthenticationResponseDto authenticate(final AuthenticationRequestDto requestDto) {
        System.out.println("Request: " + requestDto);
        final var authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(requestDto.username(), requestDto.password());
        System.out.println("Auth token: " + authToken);
        try {
            final var auth = authenticationManager.authenticate(authToken);
            System.out.println("Authenticated: " + auth);
            final var token = jwtService.generateToken(requestDto.username());
            System.out.println("Token: " + token);
            return new AuthenticationResponseDto(token);
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw e;
        }
    }



}
