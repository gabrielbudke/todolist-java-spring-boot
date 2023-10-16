package br.com.gabrielsousa.todolist.controllers;

import br.com.gabrielsousa.todolist.entities.Task;
import br.com.gabrielsousa.todolist.services.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
        task.setUserId((UUID) request.getAttribute("userId"));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(task));
    }

    @GetMapping
    public List<Task> list(HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute("userId");
        return taskService.list(userId);
    }

    @PutMapping("/{taskId}")
    public Task update(@PathVariable UUID taskId, @RequestBody Task taskToUpdate, HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute("userId");
        Task task = taskService.update(taskId, userId, taskToUpdate);
        return taskService.create(task);
    }

}
