package com.example.todo.member.common.mapper;

import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.entity.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberMapper {

    public static Member toEntity(MemberResponseDto dto, String password) {
        return new Member(dto.getEmail(), password, dto.getName());
    }

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getId(), member.getEmail(), member.getPassword());
    }
}
