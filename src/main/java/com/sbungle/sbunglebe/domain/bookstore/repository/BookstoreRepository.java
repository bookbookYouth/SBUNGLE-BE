package com.sbungle.sbunglebe.domain.bookstore.repository;

import com.sbungle.sbunglebe.domain.bookstore.dto.response.BestBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.dto.response.RecommandBookstoreResponseDto;
import com.sbungle.sbunglebe.domain.bookstore.entity.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookstoreRepository extends JpaRepository<Bookstore, Long> {

    @Query("""
    select e
    from Bookstore e
    order by e.likeCount desc
    limit 5      
    """)
    public List<Bookstore> getBookstoreByLikeCount();

    @Query(value = """
    SELECT b.id AS id,
           b.image_url AS imageUrl,
           b.name AS name,
           b.address AS address,
           CASE WHEN bl.id IS NOT NULL THEN true ELSE false END AS isLike
    FROM bookstore b
    LEFT JOIN bookstore_like bl ON b.id = bl.bookstore_id
    ORDER BY RANDOM()
    LIMIT 4
""", nativeQuery = true)
    List<RecommandBookstoreResponseDto> findRandomBookstores();


}
