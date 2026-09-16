package com.practice.springbootpractice5session.service;

import com.practice.springbootpractice5session.domain.TodoUser;
import com.practice.springbootpractice5session.exception.UserNotFoundException;
import com.practice.springbootpractice5session.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void updateUser(Long id, TodoUser changeThing) {

        TodoUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디를 찾을 수 없습니다."));
        existingUser.setUserId((changeThing.getUserId()));
        existingUser.setUserName((changeThing.getUserName()));

        String encodedPassword = passwordEncoder.encode(changeThing.getUserPassword());
        existingUser.setUserPassword(encodedPassword);

        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public boolean login(String userId, String userPassword) {

        TodoUser existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디를 찾을 수 없습니다."));
        if(passwordEncoder.matches(userPassword, existingUser.getUserPassword())) {

            return true;
        }

        return false;
    }
}
