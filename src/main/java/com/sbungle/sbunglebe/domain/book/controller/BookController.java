package com.sbungle.sbunglebe.domain.book.controller;


import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.dto.response.BookListResponse;
import com.sbungle.sbunglebe.domain.book.entity.enums.SortType;
import com.sbungle.sbunglebe.domain.book.service.BookService;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Operation(summary = "책 리스트 조회 및 필터링 (구매)")
    @GetMapping("")
    public ResponseEntity<BookListResponse> bookList(
            @AuthenticationPrincipal User currentUser,
            @RequestParam SortType sortType,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) List<String> mood,
            @RequestParam(required = false) String storeId,
            @RequestParam int page,
            @RequestParam int size
            ) {
        String userId = currentUser.getUsername();
        log.debug("도서 리스트 조회(구매) userID: {}", userId);

        BookListResponse response = bookService.getBookList(userId, sortType, genres, mood, Optional.ofNullable(storeId), page, size);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "책 리스트 조회 및 필터링 (선물)")
    @GetMapping("/presents")
    public ResponseEntity<BookListResponse> bookListForPresent(
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required = false) List<String> sex,
            @RequestParam(required = false) List<String> age,
            @RequestParam(required = false) List<String> relationship,
            @RequestParam int page,
            @RequestParam int size
    ) {
        String userId = currentUser.getUsername();
        log.debug("도서 리스트 조회(선물) userID: {}", userId);

        BookListResponse response = bookService.getBookListForPresent(userId, sex, age, relationship, page, size);
       return ResponseEntity.ok(response);
    }
}
