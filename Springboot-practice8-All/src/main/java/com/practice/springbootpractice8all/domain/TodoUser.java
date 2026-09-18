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

    @NotBlank
    @Column(length = 50, unique = true)
    private String userId;

    @NotBlank
    @Column(length = 50)
    private String userName;

    @NotBlank
    @Column(length = 255)
    private String userPassword;

    @OneToMany(mappedBy = "owner")
    private List<Todo> todos;
}
