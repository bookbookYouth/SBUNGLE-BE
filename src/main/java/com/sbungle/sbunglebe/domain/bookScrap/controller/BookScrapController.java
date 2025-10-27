package com.sbungle.sbunglebe.domain.bookScrap.controller;

import com.sbungle.sbunglebe.domain.book.dto.vo.BookCardVo;
import com.sbungle.sbunglebe.domain.bookScrap.service.BookScrapService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scraps")
@Slf4j
public class BookScrapController {
    private final BookScrapService bookScrapService;

    @Operation(summary = "스크랩 생성")
    @PostMapping("/{bookId}")
    public void bookScrapAdd(
            @PathVariable String bookId,
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();
        log.debug("도서 스크랩 userID: {}, bookID; {}", userId, bookId);
        bookScrapService.addBookScrap(bookId, userId);
    }

    @Operation(summary = "스크랩 삭제")
    @DeleteMapping("/{bookId}")
    public void bookScrapRemove(
            @PathVariable String bookId,
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();
        log.debug("도서 스크랩 삭제 userID: {}, bookID; {}", userId, bookId);
        bookScrapService.removeBookScrap(bookId, userId);
    }

    @Operation(summary = "블라인드 북 스크랩 리스트 조회")
    @GetMapping("")
    public ResponseEntity<List<BookCardVo>> bookScrapList(@AuthenticationPrincipal User currentUser) {
        String userId = currentUser.getUsername();

        List<BookCardVo> responses = bookScrapService.getBookScrapList(userId);
        return ResponseEntity.ok(responses);
    }

}
