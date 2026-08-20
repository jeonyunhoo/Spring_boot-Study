package com.example.springbootpractice2.repository;

import com.example.springbootpractice2.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> { }
