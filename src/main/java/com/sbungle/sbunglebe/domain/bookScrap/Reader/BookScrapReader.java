package com.sbungle.sbunglebe.domain.bookScrap.Reader;

import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapErrorCode;
import com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapException;
import com.sbungle.sbunglebe.domain.bookScrap.repository.BookScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookScrapReader {
    private final BookScrapRepository bookScrapRepository;

    public BookScrap getBookScrapByBookIdAndUserId(String bookId, String userId) {
        return bookScrapRepository.findByBookIdAndUserId(bookId, userId).orElseThrow(
                () -> new BookScrapException(BookScrapErrorCode.NOT_FOUND_BOOKSCRAP, bookId)
        );
    }
}
