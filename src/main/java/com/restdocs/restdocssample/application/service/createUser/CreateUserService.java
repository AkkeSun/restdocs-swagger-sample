package com.restdocs.restdocssample.application.service.createUser;

import com.restdocs.restdocssample.application.port.in.createUser.CreateUserCommand;
import com.restdocs.restdocssample.application.port.in.createUser.CreateUserUseCase;
import com.restdocs.restdocssample.application.port.out.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateUserService implements CreateUserUseCase{

    private final CreateUserPort createUserPort;

    @Override
    public CreateUserServiceResponse create(CreateUserCommand command) {
        createUserPort.createUser(command);
        return CreateUserServiceResponse.builder().result(true).build();
    }
}
