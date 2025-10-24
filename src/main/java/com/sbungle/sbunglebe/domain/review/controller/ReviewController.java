package com.sbungle.sbunglebe.domain.review.controller;

import com.sbungle.sbunglebe.domain.review.dto.request.ReviewCreateRequest;
import com.sbungle.sbunglebe.domain.review.dto.response.ReviewResponse;
import com.sbungle.sbunglebe.domain.review.service.ReviewService;
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
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "블라인드 북 리뷰 리스트 조회(score/createdAt)")
    @GetMapping("/{bookId}")
    public ResponseEntity<List<ReviewResponse>> reviewList(
            @PathVariable String bookId,
            @RequestParam(required = false) String orderBy
    ) {
        List<ReviewResponse> responses = reviewService.getReviewList(bookId, orderBy);
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "블라인드 북 리뷰 생성")
    @PostMapping("/{bookId}")
    public void reviewAdd(
            @PathVariable String bookId,
            @AuthenticationPrincipal User currentUser,
            @RequestBody ReviewCreateRequest reviewCreateRequest
            ) {
        String userId = currentUser.getUsername();
        log.debug("리뷰 생성 userID: {}, bookID; {}", userId, bookId);
        reviewService.addReview(bookId, userId, reviewCreateRequest);
    }

}
