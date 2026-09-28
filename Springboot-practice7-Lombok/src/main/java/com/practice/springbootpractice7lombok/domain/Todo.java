package com.practice.springbootpractice7lombok.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TodoId;

    @NotBlank
    @Column(length = 255)
    private String TodoDetail;

    private boolean checkTodo;


}
