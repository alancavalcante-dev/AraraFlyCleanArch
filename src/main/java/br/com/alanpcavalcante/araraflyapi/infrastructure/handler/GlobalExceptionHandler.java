package br.com.alanpcavalcante.araraflyapi.infrastructure.handler;

import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.UsernameExists;
import br.com.alanpcavalcante.araraflyapi.domain.exceptions.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String validMessage(Exception ex, String messageDefault) {
        log.error(ex.getMessage());
        String message = ex.getMessage();
        if (message == null || message.isBlank()) {
            message = messageDefault;
        }
        return message;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exception(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtime(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(UsernameExists.class)
    public ResponseEntity<ApiErrorResponse> usernameExists(UsernameExists ex) {
        String message = validMessage(ex, "Username exists");

        Error error = new Error(HttpStatus.CONFLICT.value(), message);
        ApiErrorResponse response = new ApiErrorResponse(error);

        return ResponseEntity.status(error.getStatus()).body(response);
    }
}