package com.sbungle.sbunglebe.domain.bookstore.repository;

import com.sbungle.sbunglebe.domain.bookstore.entity.BookstoreLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreLikeRepository extends JpaRepository<BookstoreLike, Long> {
    public BookstoreLike findByBookstoreIdAndUserId(Long bookstoreId, Long userId);
}
