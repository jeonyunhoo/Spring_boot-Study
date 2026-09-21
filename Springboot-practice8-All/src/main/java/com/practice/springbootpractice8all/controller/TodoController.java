package com.practice.springbootpractice8all.controller;

import com.practice.springbootpractice8all.domain.Todo;
import com.practice.springbootpractice8all.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public void createTodo(@RequestBody Todo todo) {

        todoService.saveTodo(todo);
    }

    @PostMapping("/todo/{id}")
    public Optional<Todo> reviewForIdTodo(@PathVariable long id) {

        return todoService.reviewForIdTodo(id);
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
