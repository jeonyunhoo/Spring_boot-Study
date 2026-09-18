package com.practice.springbootpractice8all.service;

import com.practice.springbootpractice8all.domain.TodoUser;
import com.practice.springbootpractice8all.exception.UserIdNotFoundException;
import com.practice.springbootpractice8all.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(TodoUser todoUser) {

        String encodedPass = passwordEncoder.encode(todoUser.getUserPassword());
        todoUser.setUserPassword(encodedPass);

        userRepository.save(todoUser);
    }

    public List<TodoUser> reviewUser() {

        return userRepository.findAll();
    }

    public void updateUser(long id, TodoUser changeUser) {

        TodoUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("해당 ID를 찾을 수 없습니다."));
        existingUser.setUserName(changeUser.getUserName());
        existingUser.setUserId(changeUser.getUserId());

        String encodedPass = passwordEncoder.encode(changeUser.getUserPassword());
        existingUser.setUserPassword(encodedPass);

        userRepository.save(existingUser);
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }
}
