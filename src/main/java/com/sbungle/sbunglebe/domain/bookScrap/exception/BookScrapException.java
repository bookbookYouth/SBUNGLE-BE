package com.sbungle.sbunglebe.domain.bookScrap.exception;

import com.sbungle.sbunglebe.global.exception.CustomException;

public class BookScrapException extends CustomException {
    public BookScrapException(BookScrapErrorCode errorCode) {
        super(errorCode);
    }

    public BookScrapException(BookScrapErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
