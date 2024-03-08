package com.restdocs.restdocssample.adapter.in.find_user_name;

import com.restdocs.restdocssample.application.service.findUser.FindUserNameServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class FindUserNameResponse {
    private String name;

    @Builder
    FindUserNameResponse(String name) {
        this.name = name;
    }

    FindUserNameResponse of (FindUserNameServiceResponse response){
        return FindUserNameResponse.builder()
            .name(response.name())
            .build();
    }
}
