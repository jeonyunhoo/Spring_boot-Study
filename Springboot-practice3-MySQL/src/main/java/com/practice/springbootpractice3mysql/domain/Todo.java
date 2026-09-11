package com.practice.springbootpractice3mysql.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TodoWrite")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @NotBlank(message = "내용은 빈칸일 수 없습니다.")
    @Column(length = 255)
    private String todoDetail;

    private boolean is_checked; // 이전에 있었던 check 예약어 오류

    public Todo() {}

    public void setTodoId(Long todoId) {

        this.todoId = todoId;
    }

    public Long getTodoId() {

        return this.todoId;
    }

    public void setTodoDetail(String todoDetail) {

        this.todoDetail = todoDetail;
    }

    public String getTodoDetail() {

        return this.todoDetail;
    }

    public void setIs_checked(boolean is_checked) {

        this.is_checked = is_checked;
    }

    public boolean isIs_checked() {

        return this.is_checked;
    }
}
