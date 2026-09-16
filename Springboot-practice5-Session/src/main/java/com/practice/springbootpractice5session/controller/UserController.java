package com.practice.springbootpractice5session.controller;

import com.practice.springbootpractice5session.service.UserService;
import com.practice.springbootpractice5session.domain.TodoUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<TodoUser> findUser() {

        return userService.findUser();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody TodoUser todoUser) {

        userService.updateUser(id, todoUser);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest logRe, HttpSession httpSession) {

        if(userService.login(logRe.getUserId(), logRe.getUserPassword())) {

            httpSession.setAttribute("UserSessKey", logRe.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }

    @GetMapping("/mypage")
    public ResponseEntity logCheck(HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute("UserSessKey");

        if(userId == null || userId.isBlank()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userId + "님이 로그인 되어있습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute("UserSessKey");

        if(userId == null || userId.isBlank()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 되어있지 않습니다.");
        }

        httpSession.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 되었습니다.");
    }
}
