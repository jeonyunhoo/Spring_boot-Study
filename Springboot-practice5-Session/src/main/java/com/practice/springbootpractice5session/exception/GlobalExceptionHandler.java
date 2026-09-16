package com.practice.springbootpractice5session.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handlerUserNotFound(UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
