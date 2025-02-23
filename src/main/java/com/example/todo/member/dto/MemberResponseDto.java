package com.example.todo.member.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String email;
    private final String name;

    public MemberResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
