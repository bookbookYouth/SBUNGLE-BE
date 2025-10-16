package com.sbungle.sbunglebe.inquiry.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateInquiryRequest(
        @NotBlank
        String content
) {
}
