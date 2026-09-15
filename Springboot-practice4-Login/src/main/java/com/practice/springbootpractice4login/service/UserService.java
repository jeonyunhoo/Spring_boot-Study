package com.practice.springbootpractice4login.service;

import com.practice.springbootpractice4login.domain.TodoUser;
import com.practice.springbootpractice4login.excepction.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.practice.springbootpractice4login.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(TodoUser todoUser) {

        String encodedPassword = passwordEncoder.encode(todoUser.getUserPassword());
        todoUser.setUserPassword(encodedPassword);
        userRepository.save(todoUser);
    }

    public List<TodoUser> findUser() {

        return userRepository.findAll();
    }

    public void updateUser(Long id, TodoUser changeData) {

        TodoUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디의 유저를 찾지 못했습니다."));
        existingUser.setUserId((changeData.getUserId()));
        existingUser.setUserName((changeData.getUserName()));
        String encodedPassword = passwordEncoder.encode(changeData.getUserPassword());
        existingUser.setUserPassword(encodedPassword);
        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public boolean login(String userId, String userPassword) {

        TodoUser existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("아이디 오류"));
        if (passwordEncoder.matches(userPassword, existingUser.getUserPassword())) {

            return true;
        }
        return false;
    }
}
