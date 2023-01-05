package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotNull(message = "username은 필수 값입니다.")
    @Size(min =4, max=10)
    @Pattern(regexp = "^[a-z0-9]*$")
    String username;


    @NotNull(message = "password는 필수 값입니다.")
    @Size(min =8, max=15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(nullable = false)
    String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
