package com.sbungle.sbunglebe.domain.review.exception;

import com.sbungle.sbunglebe.global.exception.CustomException;

public class ReviewException extends CustomException {
    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }

    public ReviewException(ReviewErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
