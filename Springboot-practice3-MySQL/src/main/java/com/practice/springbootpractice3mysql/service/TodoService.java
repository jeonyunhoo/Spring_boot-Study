package com.practice.springbootpractice3mysql.service;

import com.practice.springbootpractice3mysql.domain.Todo;
import com.practice.springbootpractice3mysql.exception.TodoNotFoundException;
import com.practice.springbootpractice3mysql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;
    }

    public void saveTodo(Todo todo) {

        todoRepository.save(todo);
    }

    public List<Todo> getAllTodo() {

        return todoRepository.findAll();
    }

    public void updateTodo(Long todoId, Todo changeThing) {

        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("해당 id의 할 일을 찾을 수 없습니다." + todoId));
        existingTodo.setTodoDetail((changeThing.getTodoDetail()));
        existingTodo.setIs_checked(changeThing.isIs_checked());
        todoRepository.save(existingTodo);
    }

    public void deleteTodo(Long todoId) {

        todoRepository.deleteById(todoId);
    }
}
