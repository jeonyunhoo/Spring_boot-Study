package com.practice.springbootpractice6token.service;

import com.practice.springbootpractice6token.domain.TodoUser;
import com.practice.springbootpractice6token.exception.UserNotFoundException;
import com.practice.springbootpractice6token.repository.UserRepository;
import com.practice.springbootpractice6token.tokenUnit.JwtUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUnit jwtUnit;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUnit jwtUnit, RedisTemplate<String, Object> redisTemplate) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUnit = jwtUnit;
        this.redisTemplate = redisTemplate;
    }

    public void saveUser(TodoUser todoUser) {

        String encodedPass = passwordEncoder.encode(todoUser.getUserPassword());
        todoUser.setUserPassword(encodedPass);

        userRepository.save(todoUser);
    }

    public List<TodoUser> reviewUser() {

        return userRepository.findAll();
    }

    public void updateUser(Long id, TodoUser changeThing) {

        TodoUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디를 찾을 수 없습니다."));
        existingUser.setUserId((changeThing.getUserId()));
        existingUser.setUserName((changeThing.getUserName()));

        String encodedPass = passwordEncoder.encode(changeThing.getUserPassword());
        existingUser.setUserPassword((encodedPass));

        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public boolean Login(String userId, String userPassword) {

        TodoUser existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디를 찾을 수 없습니다."));
        if(passwordEncoder.matches(userPassword, existingUser.getUserPassword())) {

            return true;
        }

        return false;
    }

    public void logout(String accessToken) {

        if (!jwtUnit.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        long expiration = jwtUnit.getExpiration(accessToken);

        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }
}
