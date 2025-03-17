package com.example.taskapp.user.security.mapper;


import com.example.taskapp.user.entity.User;
import com.example.taskapp.user.security.dto.RegistrationRequestDto;
import com.example.taskapp.user.security.dto.RegistrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public User toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new User();

        user.setEmail(registrationRequestDto.email());
        user.setUsername(registrationRequestDto.username());
        user.setPassword(registrationRequestDto.password());
        return user;
    }
    public RegistrationResponseDto toRegistrationResponseDto(final User user){
        return new RegistrationResponseDto(user.getEmail(),user.getUsername());
    }

}
