package com.practice.springbootpractice6token.controller;

public class LoginRequest {

    private String userId;
    private String userPassword;

    public LoginRequest() {}

    public String getUserId() {

        return this.userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserPassword() {

        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }
}
