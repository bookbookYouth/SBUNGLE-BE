package com.sbungle.sbunglebe.domain.shoppingcart.controller;

import com.sbungle.sbunglebe.domain.shoppingcart.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "장바구니 수량 감소")
    @DeleteMapping("/{shoppingCartId}")
    public void shoppingCartRemove(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String shoppingCartId
    ) {
        String userId = currentUser.getUsername();
        log.debug("장바구니 품목 삭제 userID: {}, bookID; {}", userId, shoppingCartId);
        shoppingCartService.removeItem(userId, shoppingCartId);
    }
}
