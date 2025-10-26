package com.sbungle.sbunglebe.domain.book.util;

import com.sbungle.sbunglebe.domain.book.entity.enums.SortType;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookHelper {
    public Sort getSortOrder(SortType sortType) {
        return switch (sortType) {
            case RECOMMEND -> Sort.by(Sort.Order.desc("reviewTotalScore"), Sort.Order.desc("likeCount"));
            case RESENT -> Sort.by(Sort.Order.desc("createdAt"));
            case POPULAR -> Sort.by(Sort.Order.desc("likeCount"));
            case REVIEW_COUNT -> Sort.by(Sort.Order.desc("reviewCount"));
        };
    }

    public List<PreferredGenre> convertGenres(List<String> genreStrings) {
        if (genreStrings == null) return Collections.emptyList();
        return genreStrings.stream()
                .map(str -> {
                    try {
                        return PreferredGenre.valueOf(str.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        log.warn("Invalid genre value: {}", str);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public List<PreferredType> convertMoods(List<String> moodStrings) {
        if (moodStrings == null) return Collections.emptyList();
        return moodStrings.stream()
                .map(str -> {
                    try {
                        return PreferredType.valueOf(str.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        log.warn("Invalid mood value: {}", str);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}

