package com.practice.springbootpractice8all.repository;

import com.practice.springbootpractice8all.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {


}
