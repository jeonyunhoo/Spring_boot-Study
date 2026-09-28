package com.practice.springbootpractice8all.controller;

import com.practice.springbootpractice8all.domain.Todo;
import com.practice.springbootpractice8all.service.TodoService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final HttpSession httpSession;

    @PostMapping("/todo")
    public ResponseEntity createTodo(@RequestBody Todo todo, HttpSession httpSession) {

        String userId =(String)httpSession.getAttribute("UserSessKey");

        if(userId == null || userId.isBlank()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("확인되지 않은 요청");
        }

        todoService.saveTodo(todo, userId);
        return ResponseEntity.status(HttpStatus.OK).body("등록이 완료되었습니다.");
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
