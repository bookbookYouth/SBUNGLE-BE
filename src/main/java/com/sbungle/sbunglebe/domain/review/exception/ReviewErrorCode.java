package com.sbungle.sbunglebe.domain.review.exception;

import com.sbungle.sbunglebe.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없습니다."),
    ALREADY_LIKE_OR_UNLIKE(HttpStatus.BAD_REQUEST, "이미 좋아요 혹은 싫어요를 잘못 눌렀습니다."),
    NOT_FOUND_REVIEW_LIKE(HttpStatus.NOT_FOUND, "해당 리뷰 좋아요를 찾을 수 없습니다.")
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
