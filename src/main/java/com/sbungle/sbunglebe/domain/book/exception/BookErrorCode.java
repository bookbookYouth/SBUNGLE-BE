package com.sbungle.sbunglebe.domain.book.exception;

import com.sbungle.sbunglebe.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
    NOT_FOUND_BOOK(HttpStatus.NOT_FOUND, "해당하는 도서가 존재하지 않습니다."),
    NOT_FOUND_BOOKSTORE(HttpStatus.NOT_FOUND, "해당하는 서점이가 존재하지 않습니다."),
    NOT_FOUND_BOOKSTORELIKE(HttpStatus.NOT_FOUND, "해당하는 서점 찜하기가 존재하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당하는 회원이 존재하지 않습니다.");




    private final HttpStatus httpStatus;
    private final String message;
}
