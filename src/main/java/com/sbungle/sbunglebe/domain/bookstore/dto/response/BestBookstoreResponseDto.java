package com.sbungle.sbunglebe.domain.bookstore.dto.response;

import com.sbungle.sbunglebe.domain.bookstore.entity.Bookstore;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Getter
public class BestBookstoreResponseDto {

    private Long id;
    private String bookstoreId;
    private String name;
    private String imageUrl;


    public static BestBookstoreResponseDto from(Bookstore bookstore) {
        return new BestBookstoreResponseDto(
                bookstore.getId(),
                bookstore.getBookstoreId(),
                bookstore.getName(),
                bookstore.getImageUrl()
        );
    }
}
