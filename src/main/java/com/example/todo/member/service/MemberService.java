package com.example.todo.member.service;

import com.example.todo.member.auth.dto.AuthSignupRequestDto;
import com.example.todo.member.common.mapper.MemberMapper;
import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.dto.MemberUpdateRequestDto;
import com.example.todo.member.dto.PasswordUpdateRequestDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(AuthSignupRequestDto requestDto) {

        Member member = new Member(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getName()
        );

        Member savedMember = memberRepository.save(member);

        MemberMapper.toDto(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {

        List<Member> members = memberRepository.findAll();

        return members.stream().map(MemberMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto find(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

        return MemberMapper.toDto(member);
    }

    @Transactional
    public MemberResponseDto update(Long memberId, MemberUpdateRequestDto requestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

        member.update(requestDto.getEmail(), requestDto.getName());

        return MemberMapper.toDto(member);
    }

    @Transactional
    public void updatePassword(Long memberId, PasswordUpdateRequestDto requestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

        if (!requestDto.getCurrentPassword().equals(member.getPassword())) {
            throw new IllegalStateException("비밀번호 틀림");
        }

        member.update(requestDto.getNewPassword());
    }

    @Transactional
    public void delete(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

        memberRepository.delete(member);
    }

    // email 로 member 찾기
    @Transactional
    public MemberResponseDto findMember(String email) {
        Member member = memberRepository.findByEmail().orElseThrow(
                () -> new IllegalStateException("그런 멤버 업슴")
        );
        return MemberMapper.toDto(member);
    }

    @Transactional
    public String findPassword(Long memberId) {
        return memberRepository.findPasswordById(memberId);
    }
}
