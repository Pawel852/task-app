package com.example.taskapp.user.security.securityservice;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Duration;
import java.time.Instant;

//@RequiredArgsConstructor
public class JwtService {
    private final String issuer;
    private final Duration ttl;
    private final JwtEncoder jwtEncoder;

    public JwtService(String issuer, Duration ttl, JwtEncoder jwtEncoder) {
        this.issuer = issuer;
        this.ttl = ttl;
        this.jwtEncoder = jwtEncoder;
    }


    public String generateToken(final String userName){
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        final var claimSet = JwtClaimsSet.builder()
                .subject(userName)
                .issuer(issuer)
                .expiresAt(Instant.now().plus(ttl))
                .build();
        try {
            return jwtEncoder.encode(JwtEncoderParameters.from(claimSet)).getTokenValue();
        }catch (Exception e){
            throw new RuntimeException("Error while generating token", e);
        }
    }
}
