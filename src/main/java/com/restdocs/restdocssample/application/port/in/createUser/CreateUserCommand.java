package com.restdocs.restdocssample.application.port.in.createUser;

import lombok.Builder;

@Builder
public record CreateUserCommand(String name, int age) {
}
