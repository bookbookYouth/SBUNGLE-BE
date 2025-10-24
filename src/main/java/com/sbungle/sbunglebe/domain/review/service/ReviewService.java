package com.sbungle.sbunglebe.domain.review.service;

import com.sbungle.sbunglebe.domain.book.service.BookService;
import com.sbungle.sbunglebe.domain.book.validator.BookValidator;
import com.sbungle.sbunglebe.domain.review.dto.request.ReviewCreateRequest;
import com.sbungle.sbunglebe.domain.review.dto.response.ReviewResponse;
import com.sbungle.sbunglebe.domain.review.entity.Review;
import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.domain.review.reader.ReviewReader;
import com.sbungle.sbunglebe.domain.review.repository.ReviewRepository;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final ReviewReader reviewReader;
    private final UserService userService;

    public List<ReviewResponse> getReviewList(String bookId, String orderBy) {
        List<ReviewResponse> reviewList = new ArrayList<>();

        if (orderBy.equals("score")) {
            reviewList = reviewRepository.findByBookIdOrderByLikeCountDesc(bookId).stream().map(
                    review -> ReviewResponse.of(review, userService.getNickNameByUserId(review.getUserId()))
            ).toList();
        }

        else{
            reviewList = reviewRepository.findByBookIdOrderByCreatedAtDesc(bookId).stream().map(
                    review -> ReviewResponse.of(review, userService.getNickNameByUserId(review.getUserId()))
            ).toList();
        }


        return reviewList;
    }

    @Transactional
    public void addReview(String bookId, String userId, ReviewCreateRequest reviewCreateRequest) {
        userService.findUserByIdOrThrow(userId);
        bookValidator.validateBookId(bookId);
        Review review = Review.createReview(bookId, userId, reviewCreateRequest);
        bookService.increaseBookreviewCount(bookId);
        bookService.updateBookReviewScore(bookId, reviewCreateRequest.score());
        reviewRepository.save(review);
    }

    @Transactional
    public void updateCount(String reviewId, LikeType likeType, int count) {
        Review review = reviewReader.getReviewByReviewId(reviewId);
        if (likeType == LikeType.LIKE) {
            review.increaseLikeCount();
        } else {
            review.decreaseLikeCount();
        }
    }

}
