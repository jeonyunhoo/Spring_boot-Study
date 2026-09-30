package com.practice.springbootpractice9sociallogin.controller;

import com.practice.springbootpractice9sociallogin.domain.TodoUser;
import com.practice.springbootpractice9sociallogin.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    final private UserService userService;
    final private HttpSession httpSession;

    @PostMapping("/user")
    public void createUser(TodoUser todoUser) {

        userService.saveUser(todoUser);
    }
}
