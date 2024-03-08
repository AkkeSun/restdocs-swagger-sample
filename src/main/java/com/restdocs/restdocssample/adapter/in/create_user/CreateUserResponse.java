package com.restdocs.restdocssample.adapter.in.create_user;

import com.restdocs.restdocssample.application.service.createUser.CreateUserServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class CreateUserResponse {
    private boolean result;

    @Builder
    CreateUserResponse(boolean result) {
        this.result = result;
    }

    CreateUserResponse of(CreateUserServiceResponse response) {
        return CreateUserResponse.builder()
            .result(response.result())
            .build();
    }
}
