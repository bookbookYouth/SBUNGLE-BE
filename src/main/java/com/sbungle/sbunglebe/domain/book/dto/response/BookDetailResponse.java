package com.sbungle.sbunglebe.domain.book.dto.response;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.entity.Category;
import com.sbungle.sbunglebe.global.util.formatter.PriceFormatter;
import lombok.Getter;

public record BookDetailResponse (
    String bookId,
    String title,
    String category,
    PriceFormatter price,
    String sentence
) {
    public static BookDetailResponse from(Book book) {
        return new BookDetailResponse(
                book.getBookId(),
                book.getTitle(),
                book.getCategory().getValue(),
                new PriceFormatter(book.getPrice()),
                book.getSentence()
        );
    }

}
