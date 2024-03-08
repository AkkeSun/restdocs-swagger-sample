package com.restdocs.restdocssample.adapter.in.find_user_name;

import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameCommand;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class FindUserNameRequest {

    @Min(value = 1, message = "id 는 필수값 입니다")
    private int id;

    @Builder
    public FindUserNameRequest(int id) {
        this.id = id;
    }

    public FindUserNameCommand toCommand(){
        return FindUserNameCommand.builder().id(id).build();
    }
}
