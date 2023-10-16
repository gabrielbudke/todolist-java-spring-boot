package br.com.gabrielsousa.todolist.services;

import br.com.gabrielsousa.todolist.entities.Task;
import br.com.gabrielsousa.todolist.entities.utils.TaskUtil;
import br.com.gabrielsousa.todolist.exceptionhandler.TaskInvalidDatesException;
import br.com.gabrielsousa.todolist.exceptionhandler.TaskNotFoundException;
import br.com.gabrielsousa.todolist.exceptionhandler.TaskStartAtInvalidException;
import br.com.gabrielsousa.todolist.exceptionhandler.UserNotAllowedToUpdateTaskException;
import br.com.gabrielsousa.todolist.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private ITaskRepository taskRepository;

    @Autowired
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        LocalDateTime currentDate = LocalDateTime.now();

        if(task.getStartAt().isBefore(currentDate)) {
            throw new TaskStartAtInvalidException("The start date must be greater than the current date");
        }

        if(task.getStartAt().isAfter(task.getEndAt())) {
            throw  new TaskInvalidDatesException("The end date must be greater than the start date");
        }

        return taskRepository.save(task);
    }

    public List<Task> list(UUID userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task update(UUID taskId, UUID userId, Task taskToUpdate) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id " + taskId));

        if(!task.getUserId().equals(userId)) {
            throw new UserNotAllowedToUpdateTaskException("User does not have permission to update this task");
        }

        TaskUtil.copyNonNullProperties(taskToUpdate, task);
        return taskRepository.save(task);
    }
}
