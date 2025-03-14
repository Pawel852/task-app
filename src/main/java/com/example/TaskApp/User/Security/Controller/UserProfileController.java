package com.example.TaskApp.User.Security.Controller;


import com.example.TaskApp.User.Mapper.UserMapper;
import com.example.TaskApp.User.Security.Dto.UserProfileDto;
import com.example.TaskApp.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
//@RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserProfileController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(
            final Authentication authentication
            )
        {
            final var user = userService.getUserByUserName(
                    authentication.getName());
            return ResponseEntity.ok(userMapper.toUserProfileDto(user));
        }

}
