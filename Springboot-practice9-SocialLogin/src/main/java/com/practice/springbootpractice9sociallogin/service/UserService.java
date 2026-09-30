package com.practice.springbootpractice9sociallogin.service;

import com.practice.springbootpractice9sociallogin.domain.TodoUser;
import com.practice.springbootpractice9sociallogin.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public void saveUser(TodoUser todoUser) {

        String EncodedPass = passwordEncoder.encode(todoUser.getUserPassword());
        todoUser.setUserPassword(EncodedPass);
        userRepository.save(todoUser);
    }

    public TodoUser socialFindUser(String provider, String providerId, String userEmail) {

        return userRepository.findByProviderAndProviderId(provider, providerId).orElseGet(() -> {

            TodoUser newUser = new TodoUser();
            newUser.setProvider(provider);
            newUser.setProviderId(providerId);
            newUser.setUserEmail(userEmail);
            return userRepository.save(newUser);
        });
    }

    public List<TodoUser> reviewUser() {

        return userRepository.findAll();
    }

    public void updateUser(long id, TodoUser changeThing) {


    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }
}
