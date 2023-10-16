package br.com.gabrielsousa.todolist.exceptionhandler;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
