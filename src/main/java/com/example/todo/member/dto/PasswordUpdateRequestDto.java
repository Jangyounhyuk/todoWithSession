package com.example.todo.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PasswordUpdateRequestDto {

    private String currentPassword;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String newPassword;
}
