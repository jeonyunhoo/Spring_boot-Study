package com.practice.springbootpractice7lombok.service;

import com.practice.springbootpractice7lombok.domain.Todo;
import com.practice.springbootpractice7lombok.exception.TodoIdNotFoundException;
import com.practice.springbootpractice7lombok.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void saveTodo(Todo todo) {

        todoRepository.save(todo);
    }

    public List<Todo> reviewTodo() {

        return todoRepository.findAll();
    }

    public void updateTodo(long todoId, Todo changeThing) {

        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoIdNotFoundException("해당 Id를 찾을 수 없습니다."));
        existingTodo.setTodoDetail(changeThing.getTodoDetail());
        existingTodo.setCheckTodo(changeThing.isCheckTodo());
        todoRepository.save(existingTodo);
    }

    public void deleteTodo(long todoId) {

        todoRepository.deleteById(todoId);
    }
}
