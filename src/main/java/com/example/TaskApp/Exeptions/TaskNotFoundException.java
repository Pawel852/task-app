package com.example.TaskApp.Exeptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
    }
}
