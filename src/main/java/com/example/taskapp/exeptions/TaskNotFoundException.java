package com.example.taskapp.exeptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
    }
}
