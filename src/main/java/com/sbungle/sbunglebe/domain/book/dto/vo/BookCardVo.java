package com.sbungle.sbunglebe.domain.book.dto.vo;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.global.util.formatter.PriceFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookCardVo {
    private String bookId;
    private String title;
    private String category;
    private PriceFormatter price;
    private boolean isScrap;

    public static BookCardVo from(Book book) {
        return BookCardVo.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .category(book.getGenre().getDescription())
                .price(new PriceFormatter(book.getPrice()))
                .isScrap(true) //추후 수정
                .build();
    }

    public static BookCardVo of(Book book, boolean isScrap) {
        return BookCardVo.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .category(book.getGenre().getDescription())
                .price(new PriceFormatter(book.getPrice()))
                .isScrap(isScrap)
                .build();
    }
}
