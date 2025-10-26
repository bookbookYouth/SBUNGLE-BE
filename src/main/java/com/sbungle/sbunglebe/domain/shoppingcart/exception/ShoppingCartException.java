package com.sbungle.sbunglebe.domain.shoppingcart.exception;

import com.sbungle.sbunglebe.global.exception.CustomException;

public class ShoppingCartException extends CustomException {
    public ShoppingCartException(ShoppingCartErrorCode errorCode) {
        super(errorCode);
    }

    public ShoppingCartException(ShoppingCartErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
