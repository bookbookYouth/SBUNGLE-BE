package com.sbungle.sbunglebe.domain.bookstore.entity;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookstoreLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bookstoreId")
    public Bookstore bookstore;

}
