package com.sbungle.sbunglebe.domain.bookstore.service;

import com.sbungle.sbunglebe.domain.book.exception.BookErrorCode;
import com.sbungle.sbunglebe.domain.book.exception.BookException;
import com.sbungle.sbunglebe.domain.bookstore.dto.response.BestBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.dto.response.RecommandBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.entity.Bookstore;
import com.sbungle.sbunglebe.domain.bookstore.entity.BookstoreLike;
import com.sbungle.sbunglebe.domain.bookstore.repository.BookstoreLikeRepository;
import com.sbungle.sbunglebe.domain.bookstore.repository.BookstoreRepository;
import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;
    private final BookstoreLikeRepository bookstoreLikeRepository;
    private final UserRepository userRepository;

    public List<BestBookstoreResponseDto> getBestBookstoreList() {
        return bookstoreRepository.getBookstoreByLikeCount().stream().map(
                BestBookstoreResponseDto::from
        ).toList();
    }

    public List<RecommandBookstoreResponseDto> getRecommandBookstoreList(User currentUser) {

        UserEntity user = userRepository.findByUserId(currentUser.getUsername())
                .orElseThrow(() -> new BookException(BookErrorCode.NOT_FOUND_USER));

        return bookstoreRepository.findRandomBookstores(user.getId());
    }

    public Bookstore getBookstoreDetail(User currentUser, Long bookstoreId) {
        return bookstoreRepository.findById(bookstoreId)
                .orElseThrow(() -> new BookException(BookErrorCode.NOT_FOUND_BOOKSTORE));
    }

    @Transactional
    public void flipBookstoreLikeFlag(User currentUser, Long bookstoreId) {
        UserEntity user = userRepository.findByUserId(currentUser.getUsername())
                .orElseThrow(() -> new BookException(BookErrorCode.NOT_FOUND_USER));

        Bookstore bookstore = bookstoreRepository.findById(bookstoreId)
                .orElseThrow(() -> new BookException(BookErrorCode.NOT_FOUND_BOOKSTORE));

        bookstoreLikeRepository.findByBookstoreIdAndUserId(bookstoreId, user.getId())
                .ifPresentOrElse(
                        bookstoreLike -> {
                            bookstoreLikeRepository.delete(bookstoreLike);
                            bookstore.decreaseLikeCount();
                        },
                        () -> {
                            bookstoreLikeRepository.save(
                                    BookstoreLike.builder()
                                            .user(user)
                                            .bookstore(bookstore)
                                            .build()
                            );
                            bookstore.increaseLikeCount();
                        }
                );

        bookstoreRepository.save(bookstore);
    }

}
