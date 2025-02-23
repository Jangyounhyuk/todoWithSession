package com.example.todo.member.common.mapper;

import com.example.todo.todo.dto.TodoResponseDto;
import com.example.todo.todo.entity.Todo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoMapper {

    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContent());
    }
}
