package com.example.todo.todo.controller;

import com.example.todo.member.common.consts.Const;
import com.example.todo.todo.dto.TodoRequestDto;
import com.example.todo.todo.dto.TodoResponseDto;
import com.example.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @Validated @RequestBody TodoRequestDto requestDto
    ) {
        return new ResponseEntity<>(todoService.get(memberId, requestDto),HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @Validated @RequestBody TodoRequestDto requestDto
    ) {
        return ResponseEntity.ok(todoService.update(memberId, todoId, requestDto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId
    ) {
        todoService.delete(memberId, todoId);
    }
}
