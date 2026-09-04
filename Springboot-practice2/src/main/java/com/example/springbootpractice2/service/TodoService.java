package com.example.springbootpractice2.service;

import com.example.springbootpractice2.domain.Todo;
import com.example.springbootpractice2.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;
    }

    // CRUD 중 C(create 만들기)
    public void saveTodo(Todo todo) {

        todoRepository.save(todo);
    }

    // CRUD 중 R(read 읽기)
    public List<Todo> getAllTodos() {

        return todoRepository.findAll();
    }

    // CRUD 중 U(update 수정)
    public void updateTodo(Long id, Todo changeThing) {

        Todo existingTodo = todoRepository.findById(id).get();
        existingTodo.setTodoDetail(changeThing.getTodoDetail());
        existingTodo.setCheck(changeThing.isCheck());
        todoRepository.save(existingTodo);
    }

    // CRUD 중 D(delete 제거)
    public void deleteTodo(Long id) {

        todoRepository.deleteById(id);
    }
}