package com.practice.springbootpractice8all.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todo_user_a")
public class TodoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "유저 아이디는 비어있을 수 없습니다.")
    @Column(length = 50, unique = true)
    private String userId;

    @NotBlank(message = "유저 이름은 비어있을 수 없습니다.")
    @Column(length = 50)
    private String userName;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Column(length = 255)
    private String userPassword;

    @OneToMany(mappedBy = "owner")
    private List<Todo> todos;
}