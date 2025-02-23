package com.example.todo.todo.entity;

import com.example.todo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Todo(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
