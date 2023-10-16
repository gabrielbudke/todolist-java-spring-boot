package br.com.gabrielsousa.todolist.exceptionhandler;

import lombok.Data;

@Data
public class ResponseHandler {

    private int status;
    private String message;

    public ResponseHandler(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
