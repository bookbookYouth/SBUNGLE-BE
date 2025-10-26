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
    public List<BestBookstoreResponseDto> getBookstoreByLikeCount();

    @Query(value = "SELECT id, image_url, name, address, case when bl.id is not null then 1 else 0 end as likeflag FROM bookstore b INNER JOIN bookstorelike bl ON b.id = bl.bookstoreId ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<RecommandBookstoreResponseDto> findRandomIds();
}
