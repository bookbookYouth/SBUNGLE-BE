package com.sbungle.sbunglebe.domain.shoppingcart.service;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.util.BookReader;
import com.sbungle.sbunglebe.domain.book.validator.BookValidator;
import com.sbungle.sbunglebe.domain.bookstore.service.BookstoreService;
import com.sbungle.sbunglebe.domain.shoppingcart.dto.CartItemResponse;
import com.sbungle.sbunglebe.domain.shoppingcart.entity.ShoppingCart;
import com.sbungle.sbunglebe.domain.shoppingcart.exception.ShoppingCartErrorCode;
import com.sbungle.sbunglebe.domain.shoppingcart.exception.ShoppingCartException;
import com.sbungle.sbunglebe.domain.shoppingcart.repository.ShoppingCartRepository;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoppingCartService {


    private final ShoppingCartRepository shoppingCartRepository;
    private final BookValidator bookValidator;
    private final UserService userService;
    private final BookReader bookReader;
    private final BookstoreService bookstoreService;

    public List<CartItemResponse> getCartItems(String userId) {
        userService.findUserByIdOrThrow(userId);


        return shoppingCartRepository.findByUserId(userId).stream()
                .map(sc -> {
                    Book book = bookReader.getBookByBookId(sc.getBookId());
                    String storeName = bookstoreService.getStoreNameByStoreId(book.getStoreId());
                    return CartItemResponse.of(sc, book, storeName);
                })
                .toList();
    }

    @Transactional
    public void addOrIncreaseItem(String userId, String bookId) {
        userService.findUserByIdOrThrow(userId);
        bookValidator.validateBookId(bookId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndBookId(userId, bookId)
                .orElseGet(() ->  addItem(userId, bookId));
        shoppingCart.increaseQuantity();
    }

    @Transactional
    public ShoppingCart addItem(String userId, String bookId) {


        ShoppingCart shoppingCart = ShoppingCart.createShoppingCart(userId, bookId);
        return shoppingCartRepository.save(shoppingCart);

    }

    @Transactional
    public void removeItem(String userId, String shoppingCartId) {
        userService.findUserByIdOrThrow(userId);

        ShoppingCart shoppingCart = shoppingCartRepository.findByShoppingCartId(shoppingCartId)
                .orElseThrow(() -> new ShoppingCartException(ShoppingCartErrorCode.NOT_FOUND_ITEM));

        if (shoppingCart.getQuantity() > 1) {
            shoppingCart.decreaseQuantity();
        } else {
            shoppingCartRepository.delete(shoppingCart);
        }
    }


}
