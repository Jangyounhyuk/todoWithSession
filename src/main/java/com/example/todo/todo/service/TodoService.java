package com.example.todo.todo.service;

import com.example.todo.member.common.mapper.MemberMapper;
import com.example.todo.member.common.mapper.TodoMapper;
import com.example.todo.member.dto.MemberResponseDto;
import com.example.todo.member.entity.Member;
import com.example.todo.member.repository.MemberRepository;
import com.example.todo.member.service.MemberService;
import com.example.todo.todo.dto.TodoRequestDto;
import com.example.todo.todo.dto.TodoResponseDto;
import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
//    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoResponseDto save(Long memberId, TodoRequestDto requestDto) {

        // 클래스 간 정보 전달해 줄 때 dto로 전달해줘야 하는데
        // dto 에는 password가 포함되어 있지 않아 MemberService에서
        // MemberRepository로부터 memberId를 매개변수로 받아 password를 가져오는 메서드를 작성하였는데
        // 이렇게 하면 문제가 있겠죠..? 근데 방법을 모르겠습니다 ㅠㅠ
//        Member member = getMember(memberId);
        // 위 방법으로 했더니 Todo 엔티티가 Member 엔티티를 참조하고 있는데, Member가 영속성 컨텍스트에 저장되지 않은 상태라서 오류가 발생한다고 합니다..

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

        Todo todo = new Todo(
                requestDto.getTitle(),
                requestDto.getContent(),
                member);

        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.toDto(savedTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(TodoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 todo 업슴")
        );

        return TodoMapper.toDto(todo);
    }

    @Transactional
    public TodoResponseDto update(Long memberId, Long todoId, TodoRequestDto requestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

//        Member member = getMember(memberId);

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 todo 업슴")
        );

        if (!todo.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("작성자만 수정할 수 있습니다.");
        }

        todo.update(requestDto.getTitle(), requestDto.getContent());

        return TodoMapper.toDto(todo);
    }

    @Transactional
    public void delete(Long memberId, Long todoId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 member 업슴")
        );

//        Member member = getMember(memberId);

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 todo 업슴")
        );

        if (!todo.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("작성자만 수정할 수 있습니다.");
        }

        todoRepository.delete(todo);
    }

    // memberId 로 memeber 찾아오는 메서드
//    public Member getMember(Long memberId) {
//
//        MemberResponseDto memberResponseDto = memberService.find(memberId);
//        String password = memberService.findPassword(memberId);
//        Member member = MemberMapper.toEntity(memberResponseDto, password);

        // 직관적으로 member를 반환한다는 것을 보여주기 위하여 따로 변수를 생성 후 반환
//        return member;
//    }
}
