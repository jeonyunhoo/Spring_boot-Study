package com.practice.springbootpractice3mysql.controller;

import com.practice.springbootpractice3mysql.domain.Todo;
import com.practice.springbootpractice3mysql.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public void createTodo(@RequestBody @Valid Todo todo) {

        todoService.saveTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodo() {

        return todoService.getAllTodo();
    }

    @PutMapping("/todos/{todoId}")
    public void updateTodo(@PathVariable Long todoId, @RequestBody Todo changeThing) {

        todoService.updateTodo(todoId, changeThing);
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {

        todoService.deleteTodo(todoId);
    }
}
