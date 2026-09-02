package com.example.springbootpractice2.domain.controller;

import com.example.springbootpractice2.domain.Todo;
import com.example.springbootpractice2.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public void createTodo(@RequestBody Todo todo) {

        todoService.saveTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodo() {

        return todoService.getAllTodos();
    }
}
