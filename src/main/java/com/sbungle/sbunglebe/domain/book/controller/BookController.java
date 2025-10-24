package com.sbungle.sbunglebe.domain.book.controller;


import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/v1")
@Slf4j
public class BookController {
    private final BookService bookService;

    @Operation(summary = "블라인드 북 상세 조회")
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDetailResponse> bookDetail(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String bookId
    ) {
        String userId = currentUser.getUsername();
        log.debug("도서 상세 조회 userID: {}, bookID; {}", userId, bookId);

        BookDetailResponse response = bookService.getBookDetail(userId, bookId);

        return ResponseEntity.ok(response);
    }
}
