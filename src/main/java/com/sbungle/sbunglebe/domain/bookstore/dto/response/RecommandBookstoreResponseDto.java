package com.sbungle.sbunglebe.domain.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommandBookstoreResponseDto {

    private Long id;
    private String imageUrl;
    private String name;
    private String address;
    private Boolean isLike;
}
