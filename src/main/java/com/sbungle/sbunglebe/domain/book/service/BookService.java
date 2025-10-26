package com.sbungle.sbunglebe.domain.book.service;

import com.sbungle.sbunglebe.domain.book.dto.response.BookDetailResponse;
import com.sbungle.sbunglebe.domain.book.dto.response.BookListResponse;
import com.sbungle.sbunglebe.domain.book.dto.vo.BookCardVo;
import com.sbungle.sbunglebe.domain.book.entity.Book;
import com.sbungle.sbunglebe.domain.book.entity.enums.SortType;
import com.sbungle.sbunglebe.domain.book.util.BookHelper;
import com.sbungle.sbunglebe.domain.book.util.BookReader;
import com.sbungle.sbunglebe.domain.book.repository.BookRepository;
import com.sbungle.sbunglebe.domain.bookScrap.validator.BookScrapValidator;
import com.sbungle.sbunglebe.global.dto.PageInfo;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookReader bookReader;
    private final BookScrapValidator bookScrapValidator;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final BookHelper bookHelper;

    public BookListResponse getBookList(
            String userId,
            SortType sortType,
            List<String> genreStrings,
            List<String> moodStrings,
            Optional<String> storeIdOptional,
            int page,
            int size
    ) {

        List<PreferredGenre> genres = bookHelper.convertGenres(genreStrings);
        List<PreferredType> moods = bookHelper.convertMoods(moodStrings);

        Pageable sortedPageable = PageRequest.of(
                page,
                size,
                bookHelper.getSortOrder(sortType)
        );

        Page<Book> books = bookRepository.findAllByFilters(
                storeIdOptional.orElse(null),
                genres.isEmpty() ? null : genres,
                moods.isEmpty() ? null : moods,
                sortedPageable
        );

        List<BookCardVo> bookVos = books.getContent().stream()
                .map(BookCardVo::from)
                .toList();

        PageInfo pageInfo = PageInfo.of(books.getNumber(), books.getSize(), (int) books.getTotalElements(), books.getTotalPages());


        return BookListResponse.of(bookVos, pageInfo);
    }


    public BookDetailResponse getBookDetail(String userId, String bookId) {
        userService.findUserByIdOrThrow(userId); // user검증
        Book book = bookReader.getBookByBookId(bookId);
        boolean isScrap = bookScrapValidator.existsByUserIdAndBookId(userId, bookId);
        return BookDetailResponse.from(book, isScrap);

    }

    @Transactional
    public void increaseScrapCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.increaseLikeCount();
    }

    @Transactional
    public void decreaseScrapCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.decreaseLikeCount();
    }

    public BookCardVo getBookScrap(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        return BookCardVo.from(book);
    }

    @Transactional
    public void increaseBookreviewCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.increaseReviewCount();
    }

    @Transactional
    public void decreaseBookreviewCount(String bookId) {
        Book book = bookReader.getBookByBookId(bookId);
        book.decreaseReviewCount();
    }

    @Transactional
    public void updateBookReviewScore(String bookId, float score) {
        Book book = bookReader.getBookByBookId(bookId);
        book.updateReviewTotalScore(score);
    }


}
