package com.example.taskapp.user.controller;

import com.example.taskapp.exeptions.UserNotFoundException;
import com.example.taskapp.user.entity.User;
import com.example.taskapp.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTask {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;



    @Test
    public void testGetUserById(){
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(user, responseEntity.getBody());
        assertEquals("test", responseEntity.getBody().getUsername());
        assertEquals("test@test.com", responseEntity.getBody().getEmail());
        assertEquals("test", responseEntity.getBody().getPassword());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 1L;

        when(userService.getUserById(userId)).thenThrow(new RuntimeException("User not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.getUserById(userId);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testCreateUser(){
        User userToCreate = new User();
        userToCreate.setUsername("test");
        userToCreate.setEmail("test@test.com");
        userToCreate.setPassword("test");

        User userCreated = new User();
        userCreated.setId(1L);
        userCreated.setUsername("test");
        userCreated.setEmail("test@test.com");
        userCreated.setPassword("test");

        when(userService.createUser(userToCreate)).thenReturn(userCreated);

        ResponseEntity<User> responseEntity = userController.createUser(userToCreate);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(userCreated, responseEntity.getBody());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals("test", responseEntity.getBody().getUsername());
        assertEquals("test@test.com", responseEntity.getBody().getEmail());
        assertEquals("test", responseEntity.getBody().getPassword());
        verify(userService, times(1)).createUser(userToCreate);
    }

    @Test
    public void testUpdateUser(){
        Long userId = 1L;

        User userToUpdate = new User();
        userToUpdate.setId(userId);
        userToUpdate.setUsername("test");
        userToUpdate.setEmail("test@test.com");
        userToUpdate.setPassword("test");

        User userUpdated = new User();
        userUpdated.setId(userId);
        userUpdated.setUsername("test");
        userUpdated.setEmail("test@test.com");
        userUpdated.setPassword("test");

        when(userService.updateUser(userId, userToUpdate)).thenReturn(userUpdated);

        ResponseEntity<User> responseEntity = userController.updateUser(userId, userToUpdate);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(userUpdated, responseEntity.getBody());
        assertEquals("test", responseEntity.getBody().getUsername());
        assertEquals("test@test.com", responseEntity.getBody().getEmail());
        assertEquals("test", responseEntity.getBody().getPassword());

        verify(userService, times(1)).updateUser(userId, userToUpdate);
    }

    @Test
    public void testUpdateUserNotFound(){
        Long userId = 1L;

        User userToUpdate = new User();
        userToUpdate.setUsername("test");
        userToUpdate.setEmail("test@test.com");
        userToUpdate.setPassword("test");

        when(userService.updateUser(userId, userToUpdate)).thenThrow(new UserNotFoundException(userId));

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userController.updateUser(userId, userToUpdate);
        });


        assertEquals("User with id " + userId + " not found", exception.getMessage());
        verify(userService, times(1)).updateUser(userId, userToUpdate);

    }

    @Test
    public void testDeleteUser(){
        Long userId = 1L;

        doNothing().when(userService).deleteUser(userId);

        userController.deleteUser(userId);
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testDeleteUserNotFound(){
        Long userId = 1L;

        doThrow(new UserNotFoundException(userId)).when(userService).deleteUser(userId);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userController.deleteUser(userId);
        });
        assertEquals("User with id " + userId + " not found", exception.getMessage());
        verify(userService, times(1)).deleteUser(userId);

    }

}
