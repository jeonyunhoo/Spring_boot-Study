package com.example.springbootpractice2.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        
        super(message);
    }
}
