package com.tms.service;

import com.tms.dto.TaskDto;
import com.tms.model.Task;
import com.tms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepository;

    public List<Task> getAllTasks(String search){
    if(search!=null && !search.isEmpty()) {
        return taskRepository.findByTitleContainingIgnoreCase(search);
    }else{
        return taskRepository.findAll();
        }
    }
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }
    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Task> filterTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
