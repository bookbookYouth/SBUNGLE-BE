package com.sbungle.sbunglebe.domain.book.repository;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.review.entity.Review;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookId(String bookId);
    boolean existsByBookId(String bookId);

    @Query("""
        SELECT b
        FROM Book b
        WHERE (:storeId IS NULL OR b.storeId = :storeId)
          AND (:genres IS NULL OR b.genre IN :genres)
          AND (:moods IS NULL OR b.mood IN :moods)
    """)
    Page<Book> findAllByFilters(
            @Param("storeId") String storeId,
            @Param("genres") List<PreferredGenre> genres,
            @Param("moods") List<PreferredType> moods,
            Pageable pageable
    );
}
