package com.sbungle.sbunglebe.domain.book.dto.response;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.global.util.formatter.PriceFormatter;

public record BookDetailResponse (
    String bookId,
    String title,
    String category,
    PriceFormatter price,
    String sentence,
    String averageScore,
    boolean isScrap
) {
    public static BookDetailResponse from (Book book, boolean isScrap) {
        return new BookDetailResponse(
                book.getBookId(),
                book.getTitle(),
                book.getGenre().getDescription(),
                new PriceFormatter(book.getPrice()),
                book.getSentence(),
                String.format("%.2f", (book.getReviewTotalScore()/(double) book.getReviewCount())),
                isScrap

        );
    }

}
