package com.sbungle.sbunglebe.domain.shoppingcart.dto;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.shoppingcart.entity.ShoppingCart;

public record CartItemResponse(
        String shoppingCartId,
        String bookId,
        String genre,
        String storeName,
        String title,
        int quantity,
        int originPrice,
        long totalPrice
) {
    public static CartItemResponse of(
            ShoppingCart shoppingCart,
            Book book,
            String storeName
    ) {
        int quantity = shoppingCart.getQuantity();
        int price = book.getPrice();
        long totalPrice = (long) price * quantity;
        return new CartItemResponse(
                shoppingCart.getShoppingCartId(),
                shoppingCart.getBookId(),
                book.getGenre().getDescription(),
                storeName,
                book.getTitle(),
                quantity,
                price,
                totalPrice
        );
    }
}
