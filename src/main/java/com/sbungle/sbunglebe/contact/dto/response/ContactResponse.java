package com.sbungle.sbunglebe.contact.dto.response;

import com.sbungle.sbunglebe.contact.domain.UserContact;

import java.time.LocalDateTime;

public record ContactResponse(
        Long id,
        String content,
        LocalDateTime createdAt
) {
    public static ContactResponse from(UserContact contact) {
        return new ContactResponse(
                contact.getId(),
                contact.getContent(),
                contact.getCreatedAt()
        );
    }
}
