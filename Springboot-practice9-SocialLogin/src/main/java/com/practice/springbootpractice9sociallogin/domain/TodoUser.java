package com.practice.springbootpractice9sociallogin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todoUser4")
public class TodoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50, unique = true)
    private String userId;

    @Column(length = 255)
    private String userPassword;

    @Column(length = 255)
    @Email
    private String userEmail;

    private String provider;
    private String providerId;
}