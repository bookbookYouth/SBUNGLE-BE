package com.sbungle.sbunglebe.domain.shoppingcart.service;

import com.sbungle.sbunglebe.domain.book.validator.BookValidator;
import com.sbungle.sbunglebe.domain.shoppingcart.entity.ShoppingCart;
import com.sbungle.sbunglebe.domain.shoppingcart.repository.ShoppingCartRepository;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoppingCartService {


    private final ShoppingCartRepository shoppingCartRepository;
    private final BookValidator bookValidator;
    private final UserService userService;

    @Transactional
    public void addOrIncreaseItem(String userId, String bookId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndBookId(userId, bookId)
                .orElseGet(() ->  addItem(userId, bookId));
        shoppingCart.increaseQuantity();
    }

    @Transactional
    public ShoppingCart addItem(String userId, String bookId) {
        userService.findUserByIdOrThrow(userId);
        bookValidator.validateBookId(bookId);

        ShoppingCart shoppingCart = ShoppingCart.createShoppingCart(userId, bookId);
        return shoppingCartRepository.save(shoppingCart);

    }


}
