package com.practice.springbootpractice8all.repository;

import com.practice.springbootpractice8all.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser, Long> {

    Optional<TodoUser> findByUserId(String userId);
}
