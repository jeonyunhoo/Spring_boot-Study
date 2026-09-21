package com.practice.springbootpractice8all.controller;

import com.practice.springbootpractice8all.domain.TodoUser;
import com.practice.springbootpractice8all.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void createUser(@RequestBody TodoUser todoUser) {

        userService.saveUser(todoUser);
    }

    @GetMapping("/user")
    public List<TodoUser> reviewUser() {

        return userService.reviewUser();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable long id, @RequestBody TodoUser changeThing) {

        userService.updateUser(id, changeThing);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {

        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {

        if(userService.login(loginRequest.getUserId(), loginRequest.getUserPassword())) {

            httpSession.setAttribute("UserSessKey", loginRequest.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}
