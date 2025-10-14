package com.sbungle.sbunglebe.domain.book.exception;

import com.sbungle.sbunglebe.global.exception.CustomException;

public class BookException extends CustomException {
    public BookException(BookErrorCode errorCode) {
        super(errorCode);
    }

    public BookException(BookErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
