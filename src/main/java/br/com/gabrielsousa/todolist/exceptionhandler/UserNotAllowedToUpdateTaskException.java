package br.com.gabrielsousa.todolist.exceptionhandler;

public class UserNotAllowedToUpdateTaskException extends RuntimeException {
    public UserNotAllowedToUpdateTaskException() {
    }

    public UserNotAllowedToUpdateTaskException(String message) {
        super(message);
    }
}
