package com.example.todo.member.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AuthSignupRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글로만 입력해야 합니다.")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    public String name;
}
