package com.example.springbootpractice2.service;

import com.example.springbootpractice2.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;
    }
}