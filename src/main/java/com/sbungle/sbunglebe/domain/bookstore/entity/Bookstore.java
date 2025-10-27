package com.sbungle.sbunglebe.domain.bookstore.entity;

import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bookstore extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bookstore_id", unique = true, nullable = false, length = 100)
    private String bookstoreId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(
            mappedBy = "bookstore",
            cascade = CascadeType.ALL)
    @Column(name = "books")
    private Set<Book> books;

    @ElementCollection
    @CollectionTable(name = "operating_date", joinColumns = @JoinColumn(name = "store_id"))
    @MapKeyColumn(name = "day")
    @Column(name = "hours")
    private Map<String, String> operatingDate = new HashMap<>();

    @Column(name = "sns")
    private String sns;

    @Column(name = "like_count")
    private Integer likeCount;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void increaseLikeCount() {
        if (this.likeCount == null) this.likeCount = 0;
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        if (this.likeCount != null && this.likeCount > 0) {
            this.likeCount--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookstore bookstore = (Bookstore) o;
        return id != null && id.equals(bookstore.id);
    }
}






