package com.example.springbootpractice2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private long privateCode; // 함부로 접근하지 못하도록 private로 설정

    @Column(length=255)
    private String todoDetail;

    private boolean check;

    public Todo() {

    }

    public long getPrivateCode() {

        return this.privateCode;
    }

    public void setPrivateCode(long privateCode) {

        this.privateCode = privateCode;
    }

    public String getTodoDetail() {

        return this.todoDetail;
    }

    public void setTodoDetail(String todoDetail) {

        this.todoDetail = todoDetail;
    }

    public boolean isCheck() {

        return this.check;
    }

    public void setCheck(boolean check) {

        this.check = check;
    }
}
