package com.example.todo.member.repository;

import com.example.todo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail();

    String findPasswordById(Long memberId);
}
