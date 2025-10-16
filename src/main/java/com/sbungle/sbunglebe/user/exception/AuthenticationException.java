package com.sbungle.sbunglebe.user.exception;


import com.sbungle.sbunglebe.global.exception.CustomException;
import com.sbungle.sbunglebe.global.exception.ErrorCode;

public class AuthenticationException extends CustomException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
