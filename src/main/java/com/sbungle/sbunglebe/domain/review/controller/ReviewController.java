package com.sbungle.sbunglebe.domain.review.controller;

import com.sbungle.sbunglebe.domain.review.dto.request.ReviewCreateRequest;
import com.sbungle.sbunglebe.domain.review.dto.response.ReviewResponse;
import com.sbungle.sbunglebe.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{bookId}")
    public List<ReviewResponse> reviewList(
            @PathVariable String bookId,
            @RequestParam(required = false) String orderBy
    ) {
        return reviewService.getReviewList(bookId, orderBy);
    }

    // TODO: 리뷰 생성
    @PostMapping("/{bookId}")
    public void reviewAdd(
            @PathVariable String bookId,
            String userId,
            @RequestBody ReviewCreateRequest reviewCreateRequest
            ) {
        reviewService.addReview(bookId, userId, reviewCreateRequest);
    }

}
