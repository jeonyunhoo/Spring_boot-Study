package com.practice.springbootpractice9sociallogin.controller;

import com.practice.springbootpractice9sociallogin.domain.TodoUser;
import com.practice.springbootpractice9sociallogin.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/user")
    public void createUser(@RequestBody TodoUser todoUser) {

        userService.saveUser(todoUser);
    }

    @GetMapping("/user")
    public List<TodoUser> reviewUser() {

        return userService.reviewUser();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable long id, TodoUser changeThing) {

        userService.updateUser(id, changeThing);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {

        userService.deleteUser(id);
    }
}
