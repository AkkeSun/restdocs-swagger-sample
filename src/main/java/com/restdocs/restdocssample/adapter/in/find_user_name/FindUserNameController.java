package com.restdocs.restdocssample.adapter.in.find_user_name;

import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameUseCase;
import com.restdocs.restdocssample.application.service.findUser.FindUserNameServiceResponse;
import com.restdocs.restdocssample.infrastucture.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class FindUserNameController {

    private final FindUserNameUseCase findUserNameUseCase;

    @GetMapping("/user")
    ApiResponse<FindUserNameResponse> getUserName (@Valid FindUserNameRequest request) {
        FindUserNameServiceResponse response =
            findUserNameUseCase.findUserName(request.toCommand());
        return ApiResponse.ok(new FindUserNameResponse().of(response));
    }

}
