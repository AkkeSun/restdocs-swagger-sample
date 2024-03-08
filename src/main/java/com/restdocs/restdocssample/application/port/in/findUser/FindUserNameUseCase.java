package com.restdocs.restdocssample.application.port.in.findUser;

import com.restdocs.restdocssample.application.service.findUser.FindUserNameServiceResponse;

public interface FindUserNameUseCase {
    FindUserNameServiceResponse findUserName(FindUserNameCommand command);
}
