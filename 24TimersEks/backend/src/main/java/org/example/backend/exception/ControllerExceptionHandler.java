package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        System.out.println("GLOBAL EXCEPTION KALDT");

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Intern serverfejl: " + ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorMessage> arithmeticExceptionHandler(ArithmeticException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Matematisk fejl: " + ex.getMessage() + " (f.eks. division med nul)",
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
