package br.com.gabrielsousa.todolist.exceptionhandler;

public class TaskStartAtInvalidException extends RuntimeException {

    public TaskStartAtInvalidException() {}

    public TaskStartAtInvalidException(String message) {
        super(message);
    }
}
