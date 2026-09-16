package com.practice.springbootpractice5session.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todoUsers2")
public class TodoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    @Column(length = 50)
    private String userName;

    @NotBlank(message = "아이디는 비어있을 수 없습니다.")
    @Column(length = 50, unique = true)
    private String userId;

    @NotBlank(message = "비밀번호는 비워져있을 수 없습니다.")
    @Column(length = 255)
    private String userPassword;

    public TodoUser() {}

    public long getId() {

        return this.id;
    }

    public String getUserName() {

        return this.userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getUserId() {

        return this.userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }
}
