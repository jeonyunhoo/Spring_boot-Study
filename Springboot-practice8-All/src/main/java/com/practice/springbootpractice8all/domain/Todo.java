package com.practice.springbootpractice8all.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todo_write_a")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @NotBlank
    @Column(length = 255)
    private String todoDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TodoUser owner;
}
