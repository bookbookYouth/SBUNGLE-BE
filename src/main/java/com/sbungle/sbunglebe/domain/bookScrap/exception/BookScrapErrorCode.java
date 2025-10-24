package com.sbungle.sbunglebe.domain.bookScrap.exception;

import com.sbungle.sbunglebe.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookScrapErrorCode implements ErrorCode {
    NOT_FOUND_BOOKSCRAP(HttpStatus.NOT_FOUND, "해당 도서를 찜하지 않았습니다."),
    ALREADY_EXIST_BOOKSCRAP(HttpStatus.BAD_REQUEST, "이미 해당 도서를 찜했습니다.")
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
