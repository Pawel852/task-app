package com.example.TaskApp.User.Security.config;


import com.example.TaskApp.User.Security.SecurityService.JwtService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private Duration ttl = Duration.ofHours(1);

    @Bean
    public JwtEncoder jwtEncoder(){
        if (privateKey == null || publicKey == null){
            throw new IllegalStateException("Private or public key is missing");
        }
        final var jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();

        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(new JWKSet(jwk))
        );
    }


    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtService jwtService(
            @Value("${spring.application.name}") final String appName){
        return new JwtService(appName, ttl,  jwtEncoder());

    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setTtl(Duration ttl) {
        this.ttl = ttl;
    }
}
