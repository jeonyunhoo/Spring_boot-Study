package com.practice.springbootpractice6token.repository;

import com.practice.springbootpractice6token.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser, Long> {

    Optional<TodoUser> findByUserId(String userId);
}
