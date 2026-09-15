package com.practice.springbootpractice4login.repository;

import com.practice.springbootpractice4login.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser, Long> {

    Optional<TodoUser> findByUserId(String userId);
}
