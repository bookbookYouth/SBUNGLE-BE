package com.sbungle.sbunglebe.domain.shoppingcart.dto;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.shoppingcart.entity.ShoppingCart;

public record CartItemResponse(
        String shoppingCartId,
        String bookId,
        String gerne,
        // String storeName 추후 추가 예정
        String title,
        int quantity,
        int originPrice,
        int totalPrice
) {
    public static CartItemResponse of(
            ShoppingCart shoppingCart,
            Book book
    ) {
        int quantity = shoppingCart.getQuantity();
        int price = book.getPrice();
        int totalPrice = price * quantity;
        return new CartItemResponse(
                shoppingCart.getShoppingCartId(),
                shoppingCart.getBookId(),
                book.getGenre().getDescription(),
                book.getTitle(),
                quantity,
                price,
                totalPrice
        );
    }
}
