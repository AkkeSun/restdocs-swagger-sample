package com.restdocs.restdocssample.adapter.in.create_user;

import com.restdocs.restdocssample.application.port.in.createUser.CreateUserUseCase;
import com.restdocs.restdocssample.application.service.createUser.CreateUserServiceResponse;
import com.restdocs.restdocssample.infrastucture.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
class CreateUserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    ApiResponse<CreateUserResponse> createUser (@RequestBody @Valid CreateUserRequest request) {
        CreateUserServiceResponse response = createUserUseCase.create(
            request.toCommand());
        return ApiResponse.created(new CreateUserResponse().of(response));
    }
}
