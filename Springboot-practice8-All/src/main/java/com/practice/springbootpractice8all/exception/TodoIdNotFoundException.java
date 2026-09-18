package com.practice.springbootpractice8all.exception;

public class TodoIdNotFoundException extends RuntimeException {
    public TodoIdNotFoundException(String message) {
        super(message);
    }
}
