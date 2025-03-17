package com.example.taskapp.task.service;

import com.example.taskapp.exeptions.TaskNotFoundException;
import com.example.taskapp.task.entity.Task;
import com.example.taskapp.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task){
        return taskRepository.findById(id)
                .map(t ->
                        {
                            t.setName(task.getName());
                            t.setDescription(task.getDescription());
                            t.setDone(task.isDone());
                            t.setPriority(task.getPriority());
                            return taskRepository.save(t);


                        }
                        ).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
    public Task getTaskById(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }
    public List<Task> getAllTasks(Long userId){
        return taskRepository.findAll().stream().filter(task -> task.getUser().getId().equals(userId)).toList();
    }

}
