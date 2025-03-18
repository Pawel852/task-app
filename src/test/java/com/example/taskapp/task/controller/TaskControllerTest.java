package com.example.taskapp.task.controller;

import com.example.taskapp.task.entity.Task;
import com.example.taskapp.task.service.TaskService;
import com.example.taskapp.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        task.setDescription("Test Task");

        when(taskService.getTaskById(taskId)).thenReturn(task);

        ResponseEntity<Task> responseEntity = taskController.getTaskById(taskId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(task, responseEntity.getBody());

        verify(taskService, times(1)).getTaskById(taskId);

    }

    @Test
    public void testGetTaskByIdNotFound() {
        Long taskId = 1L;

        when(taskService.getTaskById(taskId)).thenThrow(new RuntimeException("Task not found"));

        try{
            taskController.getTaskById(taskId);
        }catch (RuntimeException e){
            assertEquals("Task not found", e.getMessage());
        }

        verify(taskService, times(1)).getTaskById(taskId);
    }

    @Test
    public void testCreateTask(){
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Task taskToCreate = new Task();
        taskToCreate.setName("Test Task");
        taskToCreate.setDescription("Test Task Description");

        Task createdTask = new Task();
        createdTask.setId(1L);
        createdTask.setName("Test Task");
        createdTask.setDescription("Test Task Description");
        createdTask.setUser(user);

        when(taskService.createTask(taskToCreate)).thenReturn(createdTask);


        ResponseEntity<Task> responseEntity = taskController.createTask(taskToCreate);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(createdTask, responseEntity.getBody());
        assertEquals(user, responseEntity.getBody().getUser());

        verify(taskService, times(1)).createTask(taskToCreate);


    }

    @Test
    public void testUpdateTask(){
        Long taskId = 1L;

        Task taskToUpdate = new Task();
        taskToUpdate.setName("Updated Task");
        taskToUpdate.setDescription("Updated Description");

        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setName("Updated Task");
        updatedTask.setDescription("Updated Description");

        when(taskService.updateTask(taskId, taskToUpdate)).thenReturn(updatedTask);

        ResponseEntity<Task> responseEntity = taskController.updateTask(taskId, taskToUpdate);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(updatedTask, responseEntity.getBody());
        assertEquals("Updated Task", responseEntity.getBody().getName());
        assertEquals("Updated Description", responseEntity.getBody().getDescription());

        verify(taskService, times(1)).updateTask(taskId, taskToUpdate);


    }

    @Test
    public void testDeleteTask(){
        Long taskId = 1L;

        doNothing().when(taskService).deleteTask(taskId);

        taskController.deleteTask(taskId);
        verify(taskService, times(1)).deleteTask(taskId);
    }



}
