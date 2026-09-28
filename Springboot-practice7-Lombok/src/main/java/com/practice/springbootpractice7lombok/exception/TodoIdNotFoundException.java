package com.practice.springbootpractice7lombok.exception;

public class TodoIdNotFoundException extends RuntimeException {
    public TodoIdNotFoundException(String message) {
        super(message);
    }
}
