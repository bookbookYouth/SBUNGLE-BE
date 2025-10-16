package com.sbungle.sbunglebe.contact.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateContactRequest(
        @NotBlank
        String content
) {
}
