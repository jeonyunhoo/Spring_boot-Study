package com.practice.springbootpractice5session.repository;

import com.practice.springbootpractice5session.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser, Long> {

    Optional<TodoUser> findByUserId(String userId);
}
