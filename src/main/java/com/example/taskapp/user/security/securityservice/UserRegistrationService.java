package com.example.taskapp.user.security.securityservice;

import com.example.taskapp.user.entity.Role;
import com.example.taskapp.user.entity.User;
import com.example.taskapp.user.repository.UserRepository;
import com.example.taskapp.user.security.dto.RegistrationRequestDto;
import jakarta.transaction.Transactional;
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
