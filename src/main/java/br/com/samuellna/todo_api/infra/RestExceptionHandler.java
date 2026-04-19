package br.com.samuellna.todo_api.infra;

import br.com.samuellna.todo_api.exceptions.TaskNotFoundException;
import br.com.samuellna.todo_api.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorResponse> userNotFoundHandler(UserNotFoundException e) {
        RestErrorResponse res = new RestErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<RestErrorResponse> taskNotFoundHandler(TaskNotFoundException e) {
        RestErrorResponse res = new RestErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("Erro de validação");

        RestErrorResponse response = new RestErrorResponse(
            HttpStatus.BAD_REQUEST,
            message
        );
        return ResponseEntity.badRequest().body(response);
    }
}
