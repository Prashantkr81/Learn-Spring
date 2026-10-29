package com.prashant.urlshortener.exception;

import com.prashant.urlshortener.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException (ResponseStatusException ex) {

        HttpStatus status = HttpStatus.valueOf(
                ex.getStatusCode().value()
        );

        ErrorResponse errorResponse= ErrorResponse.builder()
                .statusCode(status.value())
                .message(ex.getReason() != null
                        ? ex.getReason()
                        : status.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException (MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": "
                    + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse= ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
