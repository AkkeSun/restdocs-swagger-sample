package com.restdocs.restdocssample.application.port.out;

import com.restdocs.restdocssample.application.port.in.createUser.CreateUserCommand;

public interface CreateUserPort {
    void createUser(CreateUserCommand command);
}
