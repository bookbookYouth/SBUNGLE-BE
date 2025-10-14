package com.sbungle.sbunglebe.domain.book.repository;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<Book> findByBookId(String bookId);
}
