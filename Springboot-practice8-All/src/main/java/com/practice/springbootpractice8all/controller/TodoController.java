package com.practice.springbootpractice8all.controller;

import com.practice.springbootpractice8all.domain.Todo;
import com.practice.springbootpractice8all.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public void createTodo(Todo todo) {

        todoService.saveTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> reviewTodo() {

        return todoService.reviewTodo();
    }

    @PutMapping("/todos/{id}")
    public void updateTodo(long todoId, Todo changeTodo) {

        todoService.updateTodo(todoId, changeTodo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(long todoId) {

        todoService.deleteTodo(todoId);
    }
}
