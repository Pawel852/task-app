package com.example.taskapp.user.service;

import com.example.taskapp.exeptions.UserNotFoundException;
import com.example.taskapp.user.entity.User;
import com.example.taskapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(Long id, User user){
        return userRepository.findById(id)
                .map(u ->
                        {
                            u.setName(user.getName());
                            u.setLastName(user.getLastName());
                            u.setEmail(user.getEmail());
                            u.setPassword(user.getPassword());
                            u.setRole(user.getRole());
                            return userRepository.save(u);
                        }
                ).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserById(Long userId){

        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserName(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
    }
}
