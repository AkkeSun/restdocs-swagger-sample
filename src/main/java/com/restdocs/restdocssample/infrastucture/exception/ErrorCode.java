package com.restdocs.restdocssample.infrastucture.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 1001 - 1099 : userInfo issue

    NOT_FOUND_USER_INFO(1001, "사용자 정보를 찾을 수 없습니다"),
    EXIST_USER_INFO(1002, "이미 등록된 사용자 입니다"),

    ;


    private final int code;

    private final String message;
}

