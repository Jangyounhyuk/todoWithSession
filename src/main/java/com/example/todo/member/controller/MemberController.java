package com.example.todo.member.controller;

import com.example.todo.member.common.consts.Const;
import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.dto.MemberUpdateRequestDto;
import com.example.todo.member.dto.PasswordUpdateRequestDto;
import com.example.todo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberResponseDto> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.find(memberId));
    }

    @PutMapping("/members")
    public ResponseEntity<MemberResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @Validated @RequestBody MemberUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(memberService.update(memberId, requestDto));
    }

    @PutMapping("/members/password")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @Validated @RequestBody PasswordUpdateRequestDto requestDto
    ) {
        memberService.updatePassword(memberId, requestDto);
    }

    @DeleteMapping("/members")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
    ) {
        memberService.delete(memberId);
    }
}
