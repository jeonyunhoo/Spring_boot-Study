package com.practice.springbootpractice6token.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todoUser3")
public class TodoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50, unique = true)
    private String userId;

    @NotBlank
    @Column(length = 50)
    private String userName;

    @NotBlank
    @Column(length = 255)
    private String userPassword;

    public TodoUser() {}

    public long getId() {

        return this.id;
    }

    public String getUserId() {

        return this.userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserName() {

        return this.userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserPassword() {

        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }
}
