package com.sbungle.sbunglebe.security.exception;


import com.sbungle.sbunglebe.global.exception.CustomException;

public class TokenException extends CustomException {

    public TokenException(SecurityErrorCode errorCode) {
        super(errorCode);
    }

    public TokenException(SecurityErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
