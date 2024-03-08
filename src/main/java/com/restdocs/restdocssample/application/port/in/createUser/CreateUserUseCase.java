package com.restdocs.restdocssample.application.port.in.createUser;

import com.restdocs.restdocssample.application.service.createUser.CreateUserServiceResponse;

public interface CreateUserUseCase {
    CreateUserServiceResponse create(CreateUserCommand command);
}
