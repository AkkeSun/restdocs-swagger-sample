package com.restdocs.restdocssample.application.port.in.findUser;

import lombok.Builder;

@Builder
public record FindUserNameCommand(int id) {

}
