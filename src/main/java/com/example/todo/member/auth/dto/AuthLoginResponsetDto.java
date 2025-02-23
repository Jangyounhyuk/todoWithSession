package com.example.todo.member.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponsetDto {

    private final Long memberId;

    public AuthLoginResponsetDto(Long memberId) {
        this.memberId = memberId;
    }
}
