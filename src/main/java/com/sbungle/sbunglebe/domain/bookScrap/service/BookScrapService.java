package com.sbungle.sbunglebe.domain.bookScrap.service;

import com.sbungle.sbunglebe.domain.book.dto.vo.BookScrapVo;
import com.sbungle.sbunglebe.domain.book.reader.BookReader;
import com.sbungle.sbunglebe.domain.book.service.BookService;
import com.sbungle.sbunglebe.domain.book.validator.BookValidator;
import com.sbungle.sbunglebe.domain.bookScrap.Reader.BookScrapReader;
import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import com.sbungle.sbunglebe.domain.bookScrap.repository.BookScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookScrapService {
    private final BookScrapRepository bookScrapRepository;
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final BookScrapReader bookScrapReader;

    public boolean findByUserIdAndBookId(String userId, String bookId) {
        return bookScrapRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Transactional
    public void addBookScrap(String bookId, String userId) {
        bookValidator.validateBookId(bookId);
        BookScrap bookScrap = BookScrap.createBookScrap(bookId, userId);
        bookService.increaseScrapCount(bookId);
        bookScrapRepository.save(bookScrap);
    }

    @Transactional
    public void removeBookScrap(String bookId, String userId) {
        bookValidator.validateBookId(bookId);
        BookScrap bookScrap = bookScrapReader.getBookScrapByBookIdAndUserId(bookId, userId);
        bookScrapRepository.delete(bookScrap);
        bookService.decreaseScrapCount(bookId);
    }

    public List<BookScrapVo> getBookScrapList(String userId) {
        return bookScrapRepository.findBookIdByUserId(userId).stream().map(
                bookScrap -> bookService.getBookScrap(bookScrap.getBookId())
        ).toList();
    }


}
