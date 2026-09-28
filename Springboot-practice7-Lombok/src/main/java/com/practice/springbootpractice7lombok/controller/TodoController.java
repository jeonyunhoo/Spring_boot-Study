package com.practice.springbootpractice7lombok.controller;

import com.practice.springbootpractice7lombok.domain.Todo;
import com.practice.springbootpractice7lombok.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public void createTodo(@RequestBody Todo todo) {

        todoService.saveTodo(todo);
    }

    @GetMapping("/todos")
    public List<Todo> reviewTodo() {

        return todoService.reviewTodo();
    }

    @PutMapping("/todos/{id}")
    public void updateTodo(@PathVariable long todoId, @RequestBody Todo changeTodo) {

        todoService.updateTodo(todoId, changeTodo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable long todoId) {

        todoService.deleteTodo(todoId);
    }
}
