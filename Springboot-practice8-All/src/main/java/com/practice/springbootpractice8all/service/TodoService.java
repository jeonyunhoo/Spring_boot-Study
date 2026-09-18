package com.practice.springbootpractice8all.service;

import com.practice.springbootpractice8all.domain.Todo;
import com.practice.springbootpractice8all.exception.TodoIdNotFoundException;
import com.practice.springbootpractice8all.repository.TodoRepository;
import com.practice.springbootpractice8all.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
                .orElseThrow(() -> new TodoIdNotFoundException("해당 ID를 찾을 수 없습니다."));
        existingTodo.setTodoDetail(changeThing.getTodoDetail());
        todoRepository.save(existingTodo);
    }

    public void deleteTodo(long todoId) {

        todoRepository.deleteById(todoId);
    }
}
