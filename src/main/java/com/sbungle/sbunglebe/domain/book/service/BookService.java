package com.sbungle.sbunglebe.domain.book.service;

import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.dto.vo.BookScrapVo;
import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.reader.BookReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookReader bookReader;

    public BookDetailResponse getBookDetail(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        return BookDetailResponse.from(book);

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
