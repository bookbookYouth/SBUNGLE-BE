package com.sbungle.sbunglebe.domain.bookstore.repository;

import com.sbungle.sbunglebe.domain.bookstore.entity.BookstoreLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookstoreLikeRepository extends JpaRepository<BookstoreLike, Long> {
    Optional<BookstoreLike> findByBookstoreIdAndUserId(Long bookstoreId, Long userId);
}
