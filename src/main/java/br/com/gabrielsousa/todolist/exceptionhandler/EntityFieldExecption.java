package br.com.gabrielsousa.todolist.exceptionhandler;

public class EntityFieldExecption extends RuntimeException {
    public EntityFieldExecption() {
    }

    public EntityFieldExecption(String message) {
        super(message);
    }
}
