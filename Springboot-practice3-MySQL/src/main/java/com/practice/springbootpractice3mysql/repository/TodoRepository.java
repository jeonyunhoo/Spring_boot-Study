package com.practice.springbootpractice3mysql.repository;

import com.practice.springbootpractice3mysql.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {


}
