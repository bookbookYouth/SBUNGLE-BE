package com.sbungle.sbunglebe.domain.bookScrap.repository;

import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookScrapRepository extends JpaRepository<BookScrap, Long> {
    Optional<BookScrap> findByBookIdAndUserId(String bookId, String userId);

    @Query("""
    SELECT bs.bookId
    FROM BookScrap bs
    WHERE bs.userId = :userId
    """)
    List<BookScrap> findBookIdByUserId(String userId);

    Boolean existsByUserIdAndBookId(String userId, String bookId);
}
