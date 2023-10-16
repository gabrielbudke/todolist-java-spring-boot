package br.com.gabrielsousa.todolist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoListExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseHandler> handleUserAlreadyExists(UserAlreadyExistsException userAlreadyExistsException) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.CONFLICT.value(), userAlreadyExistsException.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseHandler> handleUserNotFound(UserNotFoundException userNotFoundException) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.NOT_FOUND.value(), userNotFoundException.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskStartAtInvalidException.class)
    public ResponseEntity<ResponseHandler> handleTaskStartAtInvalid(TaskStartAtInvalidException taskStartAtInvalidException) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.BAD_REQUEST.value(), taskStartAtInvalidException.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskInvalidDatesException.class)
    public ResponseEntity<ResponseHandler> handleTaskInvalidDates(TaskInvalidDatesException taskInvalidDatesException) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.BAD_REQUEST.value(), taskInvalidDatesException.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAllowedToUpdateTaskException.class)
    public ResponseEntity<ResponseHandler> handleUserNotAllowedToUpdateTask(UserNotAllowedToUpdateTaskException userNotAllowedToUpdateTaskException) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.FORBIDDEN.value(), userNotAllowedToUpdateTaskException.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EntityFieldExecption.class)
    public ResponseEntity<ResponseHandler> handleEntityFieldException(EntityFieldExecption entityFieldExecption) {
        ResponseHandler responseHandler = new ResponseHandler(HttpStatus.BAD_REQUEST.value(), entityFieldExecption.getMessage());
        return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
    }



}
