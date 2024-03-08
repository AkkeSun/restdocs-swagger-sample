package com.restdocs.restdocssample.adapter.in.create_user;

import com.restdocs.restdocssample.application.port.in.createUser.CreateUserCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class CreateUserRequest {

    @NotBlank(message = "name 은 필수값 입니다")
    private String name;

    @Min(value = 1, message = "age 는 필수값 입니다")
    private int age;

    @Builder
    CreateUserRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    CreateUserCommand toCommand() {
        return CreateUserCommand.builder()
            .name(name)
            .age(age)
            .build();
    }
}
