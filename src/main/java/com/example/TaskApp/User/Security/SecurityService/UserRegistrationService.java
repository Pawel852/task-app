package com.example.TaskApp.User.Security.SecurityService;

import com.example.TaskApp.User.Entity.Role;
import com.example.TaskApp.User.Entity.User;
import com.example.TaskApp.User.Repository.UserRepository;
import com.example.TaskApp.User.Security.Controller.RegistrationController;
import com.example.TaskApp.User.Security.Dto.RegistrationRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegistrationRequestDto request){
        if(userRepository.existsByUsername(request.username())
        || userRepository.existsByEmail(request.email())){
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }


}
