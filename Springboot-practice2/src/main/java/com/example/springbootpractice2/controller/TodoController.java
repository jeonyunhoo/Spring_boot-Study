package com.example.springbootpractice2.controller;

import com.example.springbootpractice2.domain.Todo;
import com.example.springbootpractice2.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createTodo(@RequestBody Todo todo) {

        todoService.saveTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodo() {

        return todoService.getAllTodos();
    }

    @PutMapping("/todos/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody Todo changeThing) {

        todoService.updateTodo(id, changeThing);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Long id) {

        todoService.deleteTodo(id);
    }
}
