package com.example.todo.member.auth.service;

import com.example.todo.member.auth.dto.AuthLoginRequestDto;
import com.example.todo.member.auth.dto.AuthLoginResponsetDto;
import com.example.todo.member.auth.dto.AuthSignupRequestDto;
import com.example.todo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    @Transactional
    public void signup(AuthSignupRequestDto requestDto) {

        memberService.save(requestDto);
    }

    @Transactional
    public AuthLoginResponsetDto login(AuthLoginRequestDto requestDto) {

        return new AuthLoginResponsetDto(
                memberService.findMember(requestDto.getEmail())
                        .getId()
        );
    }
}
