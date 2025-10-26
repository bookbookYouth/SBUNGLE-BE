package com.sbungle.sbunglebe.domain.shoppingcart.entity;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_carts")
public class ShoppingCart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shopping_cart_id", unique = true)
    private String shoppingCartId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public void increaseQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity -= 1;
        }
    }

    public static ShoppingCart createShoppingCart(String userId, String bookId) {
        return ShoppingCart.builder()
                .userId(userId)
                .bookId(bookId)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    private ShoppingCart(String userId, String bookId) {
        this.shoppingCartId = UUID.randomUUID().toString();
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = 0;
    }


}
