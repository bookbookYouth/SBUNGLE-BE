package com.sbungle.sbunglebe.domain.bookScrap.validator;

import com.sbungle.sbunglebe.domain.book.repository.BookRepository;
import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapException;
import com.sbungle.sbunglebe.domain.bookScrap.repository.BookScrapRepository;
import com.sbungle.sbunglebe.domain.bookScrap.service.BookScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapErrorCode.ALREADY_EXIST_BOOKSCRAP;
import static com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapErrorCode.NOT_FOUND_BOOKSCRAP;

@Component
@RequiredArgsConstructor
public class BookScrapValidator {
    private final BookScrapRepository bookScrapRepository;

    public boolean existsByUserIdAndBookId(String userId, String bookId) {
        return bookScrapRepository.existsByUserIdAndBookId(userId, bookId);
    }

    public void validateBookScrapPost(String userId, String bookId) {
        if(existsByUserIdAndBookId(userId, bookId)) {
            throw new BookScrapException(ALREADY_EXIST_BOOKSCRAP);
        }
    }

    public void validateBookScrapDelete(String userId, String bookId) {
        if(!existsByUserIdAndBookId(userId, bookId)) {
            throw new BookScrapException(NOT_FOUND_BOOKSCRAP);
        }
    }



}
