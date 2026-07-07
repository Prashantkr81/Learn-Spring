package com.prashant.Employee_Management_Adv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(
            EmployeeNotFoundException ex
    ){
        ErrorResponse error= new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), LocalDateTime.now()
        );

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex
    ){
        Map<String, String> errors= new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ValidationErrorResponse response=
                new ValidationErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Failed",
                        LocalDateTime.now(),
                        errors
                );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
