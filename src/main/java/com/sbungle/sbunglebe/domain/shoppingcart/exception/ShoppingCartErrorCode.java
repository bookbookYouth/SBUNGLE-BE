package com.sbungle.sbunglebe.domain.shoppingcart.exception;

import com.sbungle.sbunglebe.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ShoppingCartErrorCode implements ErrorCode {
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 장바구니 아이템을 찾을 수 없습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
