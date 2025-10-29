package com.sbungle.sbunglebe.inquiry.dto.response;

import com.sbungle.sbunglebe.inquiry.domain.Inquiry;

import java.time.LocalDateTime;

public record InquiryResponse(
        Long id,
        String content,
        LocalDateTime createdAt
) {
    public static InquiryResponse from(Inquiry contact) {
        return new InquiryResponse(
                contact.getId(),
                contact.getContent(),
                contact.getCreatedAt()
        );
    }
}
