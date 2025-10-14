package com.sbungle.sbunglebe.domain.book.service;

import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.reader.BookReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookReader bookReader;

    public BookDetailResponse getBookDetail(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        return BookDetailResponse.from(book);

    }


}
