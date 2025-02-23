package com.example.todo.member.repository;

import com.example.todo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("SELECT m.password FROM Member m WHERE m.id = :id")
    String findPasswordById(@Param("id") Long id);
}
