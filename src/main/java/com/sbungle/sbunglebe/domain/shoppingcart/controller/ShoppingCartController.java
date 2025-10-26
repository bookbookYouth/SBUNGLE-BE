package com.sbungle.sbunglebe.domain.shoppingcart.controller;

import com.sbungle.sbunglebe.domain.shoppingcart.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts/v1")
@Slf4j
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    // TODO: 장바구니 목록 조회

    @Operation(summary = "장바구니 품목 추가 및 수량 증가")
    @PostMapping("/{bookId}")
    public void shoppingCartAdd(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String bookId
    ) {
        String userId = currentUser.getUsername();
        log.debug("장바구니 품목 추가 userID: {}, bookID; {}", userId, bookId);
        shoppingCartService.addOrIncreaseItem(userId, bookId);
    }

    // TODO: 장바구리 품목 삭제
}
