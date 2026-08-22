package com.example.springbootpractice2.service;

import com.example.springbootpractice2.domain.Todo;
import com.example.springbootpractice2.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;
    }

    public void saveTodo(Todo todo) {

        todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {

        return todoRepository.findAll();
    }
}