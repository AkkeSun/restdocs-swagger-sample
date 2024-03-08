package com.restdocs.restdocssample.application.port.out;

import com.restdocs.restdocssample.domain.User;

public interface FindUserPort {
    User finUserById(int id);
}
