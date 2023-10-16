package br.com.gabrielsousa.todolist.exceptionhandler;

public class TaskInvalidDatesException extends RuntimeException {

    public TaskInvalidDatesException() {
    }

    public TaskInvalidDatesException(String message) {
        super(message);
    }
}
