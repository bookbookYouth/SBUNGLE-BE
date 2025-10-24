package com.sbungle.sbunglebe.domain.bookScrap.controller;

import com.sbungle.sbunglebe.domain.book.dto.vo.BookScrapVo;
import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import com.sbungle.sbunglebe.domain.bookScrap.service.BookScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scraps")
public class BookScrapController {
    private final BookScrapService bookScrapService;

    @PostMapping("/{bookId}")
    public void bookScrapAdd(
            @PathVariable String bookId,
            String userId
    ) { // TODO: 추후 userId를 Authentication 이용
        bookScrapService.addBookScrap(bookId, userId);
    }

    @DeleteMapping("/{bookId}")
    public void bookScrapRemove(
            @PathVariable String bookId,
            String userId
    ) { // TODO: 추후 userId를 Authentication 이용
        bookScrapService.removeBookScrap(bookId, userId);
    }

    @GetMapping("")
    public List<BookScrapVo> bookScrapList(String userId) { // TODO: 추후 userId를 Authentication 이용
        return bookScrapService.getBookScrapList(userId);
    }

}
