package com.restdocs.restdocssample.infrastucture.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int errorCode;

    private String errorMsg;

    @Builder
    public ErrorResponse(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ErrorResponse of (CustomException e){
        return ErrorResponse.builder()
            .errorCode(e.getErrorCode().getCode())
            .errorMsg(e.getErrorCode().getMessage())
            .build();
    }
}
