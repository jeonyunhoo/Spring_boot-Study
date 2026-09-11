package com.practice.springbootpractice3mysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity handlerTodoNotFound(TodoNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
