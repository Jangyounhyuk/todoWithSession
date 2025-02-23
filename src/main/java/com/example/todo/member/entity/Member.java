package com.example.todo.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void update(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void update(String newPassword) {
        this.password = newPassword;
    }
}
