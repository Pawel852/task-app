package com.example.TaskApp.User.Mapper;

import com.example.TaskApp.User.Entity.User;
import com.example.TaskApp.User.Security.Dto.UserProfileDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final User user){
        return new UserProfileDto(user.getEmail(), user.getLastName());
    }
}
