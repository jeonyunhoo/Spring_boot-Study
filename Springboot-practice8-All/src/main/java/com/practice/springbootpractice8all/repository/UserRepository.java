package com.practice.springbootpractice8all.repository;

import com.practice.springbootpractice8all.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TodoUser, Long> {
}
