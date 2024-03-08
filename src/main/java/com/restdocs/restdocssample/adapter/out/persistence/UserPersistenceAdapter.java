package com.restdocs.restdocssample.adapter.out.persistence;

import static com.restdocs.restdocssample.infrastucture.exception.ErrorCode.EXIST_USER_INFO;

import com.restdocs.restdocssample.application.port.in.createUser.CreateUserCommand;
import com.restdocs.restdocssample.application.port.out.CreateUserPort;
import com.restdocs.restdocssample.application.port.out.FindUserPort;
import com.restdocs.restdocssample.domain.User;
import com.restdocs.restdocssample.infrastucture.exception.CustomException;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements FindUserPort, CreateUserPort {

    @Override
    public User finUserById(int id) {
        if(id == 1) {
            return User.builder()
                .id(id)
                .name("od")
                .build();
        }
        if(id == 2) {
            return User.builder()
                .id(id)
                .name("sj")
                .build();
        }
        return null;
    }

    @Override
    public void createUser(CreateUserCommand command) {
        if(command.age() == 34 && command.name().equals("od")) {
            throw new CustomException(EXIST_USER_INFO);
        }
        // ..
    }
}
