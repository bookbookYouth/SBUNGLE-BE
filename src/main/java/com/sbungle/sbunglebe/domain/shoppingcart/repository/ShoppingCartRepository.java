package com.sbungle.sbunglebe.domain.shoppingcart.repository;

import com.sbungle.sbunglebe.domain.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserIdAndBookId(String userId, String bookId);
    Optional<ShoppingCart> findByShoppingCartId(String shoppingCartId);
}
