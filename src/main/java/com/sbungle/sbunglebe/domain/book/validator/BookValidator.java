package com.sbungle.sbunglebe.domain.book.validator;

import com.sbungle.sbunglebe.domain.book.exception.BookErrorCode;
import com.sbungle.sbunglebe.domain.book.exception.BookException;
import com.sbungle.sbunglebe.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository bookRepository;

    public void validateBookId(String bookId) {
        if (bookRepository.existsByBookId(bookId)) {
            throw new BookException(BookErrorCode.NOT_FOUND_BOOK, bookId);
        }
    }
}
