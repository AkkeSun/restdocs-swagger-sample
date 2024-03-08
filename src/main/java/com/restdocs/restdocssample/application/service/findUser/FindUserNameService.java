package com.restdocs.restdocssample.application.service.findUser;

import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameCommand;
import com.restdocs.restdocssample.application.port.in.findUser.FindUserNameUseCase;
import com.restdocs.restdocssample.application.port.out.FindUserPort;
import com.restdocs.restdocssample.domain.User;
import com.restdocs.restdocssample.infrastucture.exception.CustomException;
import com.restdocs.restdocssample.infrastucture.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
class FindUserNameService implements FindUserNameUseCase {

    private final FindUserPort findUserPort;

    @Override
    public FindUserNameServiceResponse findUserName(FindUserNameCommand command) {
        User user = findUserPort.finUserById(command.id());
        if(ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_INFO);
        }
        return FindUserNameServiceResponse.builder()
            .name(user.name())
            .build();
    }
}
