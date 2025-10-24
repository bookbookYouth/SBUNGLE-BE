package com.sbungle.sbunglebe.domain.review.dto.request;

public record ReviewCreateRequest(
        String content,
        float score,
        boolean isSpoiler
) {
}
