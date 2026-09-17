package com.practice.springbootpractice6token.controller;

import com.practice.springbootpractice6token.domain.TodoUser;
import com.practice.springbootpractice6token.service.UserService;
import com.practice.springbootpractice6token.tokenUnit.JwtUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtUnit jwtUnit;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserController(UserService userService, JwtUnit jwtUnit, RedisTemplate<String, Object> redisTemplate) {

        this.userService = userService;
        this.jwtUnit = jwtUnit;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody TodoUser todoUser) {

        userService.saveUser(todoUser);
    }

    @GetMapping("/user")
    public List<TodoUser> reviewUser() {

        return userService.reviewUser();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody TodoUser changeThing) {

        userService.updateUser(id, changeThing);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

        if(userService.Login(loginRequest.getUserId(), loginRequest.getUserPassword())) {

            return ResponseEntity.status(HttpStatus.OK).body("로그인 되었습니다. \n"
                   + jwtUnit.createToken(loginRequest.getUserId()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
    }

    @GetMapping("/mypage")
    public ResponseEntity loginCheck(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        if (redisTemplate.hasKey(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이미 로그아웃된 토큰입니다.");
        }

        String userId = jwtUnit.getUserIdFromToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(userId + "님, 로그인되었습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String bearerToken) {

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 토큰 형식입니다.");
        }

        String accessToken = bearerToken.substring(7);

        userService.logout(accessToken);

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 로그아웃되었습니다.");

    }
}