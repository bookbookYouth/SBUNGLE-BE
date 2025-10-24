package com.sbungle.sbunglebe.domain.book.dto.vo;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.global.util.formatter.PriceFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookScrapVo {
    private String bookId;
    private String title;
    private String category;
    private PriceFormatter price;

    public static BookScrapVo from(Book book) {
        return BookScrapVo.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .category(book.getGenre().getDescription())
                .price(new PriceFormatter(book.getPrice()))
                .build();
    }
}
