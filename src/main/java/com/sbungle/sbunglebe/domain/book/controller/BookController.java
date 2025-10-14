package com.sbungle.sbunglebe.domain.book.controller;


import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/{bookId}")
    public BookDetailResponse bookDetail(
            @PathVariable String bookId
    ) {
        return bookService.getBookDetail(bookId);
    }
}
