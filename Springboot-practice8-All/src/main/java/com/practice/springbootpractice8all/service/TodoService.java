package com.practice.springbootpractice8all.service;

import com.practice.springbootpractice8all.domain.Todo;
import com.practice.springbootpractice8all.domain.TodoUser;
import com.practice.springbootpractice8all.exception.TodoIdNotFoundException;
import com.practice.springbootpractice8all.exception.UserIdNotFoundException;
import com.practice.springbootpractice8all.repository.TodoRepository;
import com.practice.springbootpractice8all.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public void saveTodo(Todo todo, String userId) {

        TodoUser existingTodoUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserIdNotFoundException("해당 ID를 찾을 수 없습니다."));
        todo.setOwner(existingTodoUser);
        todoRepository.save(todo);
    }

    public List<Todo> reviewTodo() {

        return todoRepository.findAll();
    }

    public Optional<Todo> reviewForIdTodo(long id) {

        return todoRepository.findById(id);
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
