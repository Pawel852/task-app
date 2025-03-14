package com.example.TaskApp.User.Security.Mapper;


import com.example.TaskApp.User.Entity.User;
import com.example.TaskApp.User.Security.Dto.RegistrationRequestDto;
import com.example.TaskApp.User.Security.Dto.RegistrationResponseDto;
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
