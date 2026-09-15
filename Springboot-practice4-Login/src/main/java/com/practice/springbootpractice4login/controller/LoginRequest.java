package com.practice.springbootpractice4login.controller;

public class LoginRequest {

    private String userId;
    private String userPassword;

    public LoginRequest() {}

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserId() {

        return this.userId;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    public String getUserPassword() {

        return this.userPassword;
    }
}
