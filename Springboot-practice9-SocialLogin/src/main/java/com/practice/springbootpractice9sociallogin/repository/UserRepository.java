package com.practice.springbootpractice9sociallogin.repository;

import com.practice.springbootpractice9sociallogin.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TodoUser, Long> {
    Optional<TodoUser> findByUserId(String userId);
    Optional<TodoUser> findByProviderAndProviderId(String provider, String providerId);
}
