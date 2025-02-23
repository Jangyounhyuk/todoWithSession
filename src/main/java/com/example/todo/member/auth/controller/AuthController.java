package com.example.todo.member.auth.controller;

import com.example.todo.member.auth.dto.AuthLoginRequestDto;
import com.example.todo.member.auth.dto.AuthLoginResponsetDto;
import com.example.todo.member.auth.dto.AuthSignupRequestDto;
import com.example.todo.member.auth.service.AuthService;
import com.example.todo.member.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@Validated @RequestBody AuthSignupRequestDto requestDto) {
        authService.signup(requestDto);
    }

    @PostMapping("/login")
    public void login(
            @RequestBody AuthLoginRequestDto requestDto,
            HttpServletRequest request
    ) {
        AuthLoginResponsetDto result = authService.login(requestDto);

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
