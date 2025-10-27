package com.sbungle.sbunglebe.domain.bookstore.controller;

import com.sbungle.sbunglebe.domain.bookstore.dto.response.BestBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.dto.response.RecommandBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.entity.Bookstore;
import com.sbungle.sbunglebe.domain.bookstore.service.BookstoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "bookstore Controller", description = "독립서점")
@RequestMapping("/bookstores/v1")
@RequiredArgsConstructor
@Slf4j
public class BookstoreController {

    private final BookstoreService bookstoreService;

    @GetMapping("/best")
    @Operation(summary = "인기 독립서점 best5")
    public ResponseEntity<List<BestBookstoreResponseDto>> getBestBookstore(){
        return ResponseEntity.ok(bookstoreService.getBestBookstoreList());
    }

    @GetMapping("/recommand")
    @Operation(summary ="유저별 추천 서점")
    public ResponseEntity<List<RecommandBookstoreResponseDto>> getRecommandBookstore(
            @AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(bookstoreService.getRecommandBookstoreList(currentUser));

    }

    @GetMapping("/genre")
    @Operation(summary ="컨셉별 서점 추천")
    public ResponseEntity<List<RecommandBookstoreResponseDto>> getGenreBookstore(
            @AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(bookstoreService.getRecommandBookstoreList(currentUser));

    }

    @GetMapping("/{bookstoreId}")
    @Operation(summary = "서점 상세정보")
    public ResponseEntity<Bookstore> getBookstoreDetail(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long bookstoreId
    ){
        return ResponseEntity.ok(bookstoreService.getBookstoreDetail(currentUser, bookstoreId));
    }

    @PostMapping("/{bookstoreId}/like")
    @Operation(summary = "독립서점 찜하기")
    public ResponseEntity<Void> likeBookstore(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long bookstoreId
    ){
        bookstoreService.flipBookstoreLikeFlag(currentUser, bookstoreId);
        return ResponseEntity.ok().build();
    }






}
