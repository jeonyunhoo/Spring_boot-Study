package com.practice.springbootpractice4login.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todoUser")
public class TodoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "유저 아이디는 비어있을 수 없습니다.")
    @Column(length = 50)
    private String userId;

    @NotBlank(message = "유저 이름은 비어있을 수 없습니다.")
    @Column(length = 50)
    private String userName;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Column(length = 255)
    private String userPassword;

    public TodoUser() {}

    public Long getId() {

        return id;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserId() {

        return this.userId;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserName() {

        return this.userName;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    public String getUserPassword() {

        return this.userPassword;
    }
}
