package com.example.taskapp.user.mapper;

import com.example.taskapp.user.entity.User;
import com.example.taskapp.user.security.dto.UserProfileDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final User user){
        return new UserProfileDto(user.getEmail(), user.getLastName());
    }
}
