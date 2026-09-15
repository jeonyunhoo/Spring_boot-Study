package com.practice.springbootpractice4login.controller;

import com.practice.springbootpractice4login.domain.TodoUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.practice.springbootpractice4login.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody @Valid TodoUser todoUser) {

        userService.saveUser(todoUser);
    }

    @GetMapping("/user")
    public List<TodoUser> viewUser() {

        return userService.findUser();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody TodoUser changeData) {

        userService.updateUser(id, changeData);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest logRe) {

        if (userService.login(logRe.getUserId(), logRe.getUserPassword())) {

            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}
