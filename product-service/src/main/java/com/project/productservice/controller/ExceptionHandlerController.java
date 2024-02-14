package com.project.productservice.controller;

import com.project.productservice.dto.response.Base;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Base<String>> constraintViolation(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Base.<String>builder().error(exception.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build()
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Base<String>> constraintViolation(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                Base.<String>builder().error(exception.getReason()).status(exception.getStatusCode().value()).build()
        );
    }
}
