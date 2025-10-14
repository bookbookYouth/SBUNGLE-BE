package com.sbungle.sbunglebe.domain.book.reader;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.exception.BookErrorCode;
import com.sbungle.sbunglebe.domain.book.exception.BookException;
import com.sbungle.sbunglebe.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookReader {
    private final BookRepository bookRepository;

    public Book getBookByBookId(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookException(BookErrorCode.NOT_FOUND_BOOK, bookId));
    }
}
