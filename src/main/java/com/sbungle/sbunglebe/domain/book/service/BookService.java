package com.sbungle.sbunglebe.domain.book.service;

import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.dto.vo.BookScrapVo;
import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.reader.BookReader;
import com.sbungle.sbunglebe.domain.bookScrap.validator.BookScrapValidator;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookReader bookReader;
    private final BookScrapValidator bookScrapValidator;
    private final UserService userService;

    public BookDetailResponse getBookDetail(String userId, String bookId) {
        userService.findUserByIdOrThrow(userId); // user검증
        Book book = bookReader.getBookByBookId(bookId);
        boolean isScrap = bookScrapValidator.existsByUserIdAndBookId(userId, bookId);
        return BookDetailResponse.from(book, isScrap);

    }

    @Transactional
    public void increaseScrapCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.increaseLikeCount();
    }

    @Transactional
    public void decreaseScrapCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.decreaseLikeCount();
    }

    public BookScrapVo getBookScrap(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        return BookScrapVo.from(book);
    }

    @Transactional
    public void increaseBookreviewCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.increaseReviewCount();
    }

    @Transactional
    public void decreaseBookreviewCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.decreaseReviewCount();
    }

    @Transactional
    public void updateBookReviewScore(String bookId, float score) {
        Book book = bookReader.getBookByBookId(bookId);
        book.updateReviewTotalScore(score);
    }


}
