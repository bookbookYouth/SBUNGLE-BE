package com.sbungle.sbunglebe.domain.book.dto.response;

import java.util.List;
import com.sbungle.sbunglebe.domain.book.dto.vo.BookCardVo;
import com.sbungle.sbunglebe.global.dto.PageInfo;

public record BookListResponse(
        List<BookCardVo> books,
        PageInfo pageInfo
) {
    public static BookListResponse of(List<BookCardVo> books, PageInfo pageInfo) {
        return new BookListResponse(books, pageInfo);
    }
}
