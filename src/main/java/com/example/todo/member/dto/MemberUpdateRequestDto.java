package com.example.todo.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글로만 입력해야 합니다.")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
}
