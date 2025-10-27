package com.sbungle.sbunglebe.domain.book.util;

import com.sbungle.sbunglebe.domain.book.dto.vo.RuleResult;
import com.sbungle.sbunglebe.domain.book.entity.enums.AgeGroup;
import com.sbungle.sbunglebe.domain.book.entity.enums.Relation;
import com.sbungle.sbunglebe.domain.book.entity.enums.SortType;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public RuleResult ruleBasedForGift(Gender gender, AgeGroup ageGroup, Relation relation) {
        Set<PreferredGenre> genres = new LinkedHashSet<>();
        Set<PreferredType>  moods  = new LinkedHashSet<>();

        switch (relation) {
            case FRIEND -> {
                switch (ageGroup) {
                    case TEENS -> {
                        genres.addAll(List.of(
                                PreferredGenre.NOVEL,
                                PreferredGenre.POETRY_ESSAY,
                                PreferredGenre.ART
                        ));
                        moods.addAll(List.of(
                                PreferredType.CHEERFUL_LIGHT,
                                PreferredType.WITTY_CREATIVE,
                                PreferredType.CALM_WARM
                        ));
                    }
                    case TWENTIES -> {
                        genres.addAll(List.of(
                                PreferredGenre.NOVEL,
                                PreferredGenre.POETRY_ESSAY,
                                PreferredGenre.SELF_DEVELOPMENT,
                                PreferredGenre.ART
                        ));
                        moods.addAll(List.of(
                                PreferredType.SENSUAL_DREAMY,
                                PreferredType.CHEERFUL_LIGHT,
                                PreferredType.CALM_WARM,
                                PreferredType.WITTY_CREATIVE
                        ));
                    }
                    case THIRTIES -> {
                        genres.addAll(List.of(
                                PreferredGenre.HUMANITIES_SOCIAL,
                                PreferredGenre.NOVEL,
                                PreferredGenre.ECONOMY_MANAGEMENT
                        ));
                        moods.addAll(List.of(
                                PreferredType.CALM_WARM,
                                PreferredType.PHILOSOPHICAL_REFLECTIVE,
                                PreferredType.REALISTIC_SOCIAL
                        ));
                    }
                    case FORTY_PLUS -> {
                        genres.addAll(List.of(
                                PreferredGenre.HUMANITIES_SOCIAL,
                                PreferredGenre.ECONOMY_MANAGEMENT,
                                PreferredGenre.POETRY_ESSAY
                        ));
                        moods.addAll(List.of(
                                PreferredType.CALM_WARM,
                                PreferredType.PHILOSOPHICAL_REFLECTIVE
                        ));
                    }
                }
            }
            case LOVER -> {

                genres.addAll(List.of(
                        PreferredGenre.POETRY_ESSAY,
                        PreferredGenre.ART,
                        PreferredGenre.NOVEL
                ));
                moods.addAll(List.of(
                        PreferredType.SENSUAL_DREAMY,
                        PreferredType.CALM_WARM,
                        PreferredType.CHEERFUL_LIGHT
                ));

                if (ageGroup == AgeGroup.THIRTIES || ageGroup == AgeGroup.FORTY_PLUS) {
                    moods.add(PreferredType.PHILOSOPHICAL_REFLECTIVE);
                }
            }
            case FAMILY -> {
                switch (ageGroup) {
                    case TEENS -> {
                        genres.addAll(List.of(
                                PreferredGenre.NOVEL,
                                PreferredGenre.POETRY_ESSAY
                        ));
                        moods.addAll(List.of(
                                PreferredType.CHEERFUL_LIGHT,
                                PreferredType.CALM_WARM
                        ));
                    }
                    case TWENTIES, THIRTIES -> {
                        genres.addAll(List.of(
                                PreferredGenre.POETRY_ESSAY,
                                PreferredGenre.HUMANITIES_SOCIAL
                        ));
                        moods.addAll(List.of(
                                PreferredType.CALM_WARM,
                                PreferredType.PHILOSOPHICAL_REFLECTIVE
                        ));
                    }
                    case FORTY_PLUS -> {
                        genres.addAll(List.of(
                                PreferredGenre.HUMANITIES_SOCIAL,
                                PreferredGenre.POETRY_ESSAY
                        ));
                        moods.addAll(List.of(
                                PreferredType.CALM_WARM,
                                PreferredType.PHILOSOPHICAL_REFLECTIVE,
                                PreferredType.REALISTIC_SOCIAL
                        ));
                    }
                }
            }
            case COLLEAGUE -> {

                genres.addAll(List.of(
                        PreferredGenre.SELF_DEVELOPMENT,
                        PreferredGenre.ECONOMY_MANAGEMENT,
                        PreferredGenre.HUMANITIES_SOCIAL
                ));
                moods.addAll(List.of(
                        PreferredType.REALISTIC_SOCIAL,
                        PreferredType.CALM_WARM,
                        PreferredType.WITTY_CREATIVE
                ));

                if (ageGroup == AgeGroup.TEENS || ageGroup == AgeGroup.TWENTIES) {
                    moods.add(PreferredType.CHEERFUL_LIGHT);
                } else {
                    moods.add(PreferredType.PHILOSOPHICAL_REFLECTIVE);
                }
            }
        }

        if (gender == Gender.FEMALE) {
            moods.addAll(List.of(
                    PreferredType.SENSUAL_DREAMY,
                    PreferredType.CALM_WARM
            ));

            genres.add(PreferredGenre.POETRY_ESSAY);
        }
        if (gender == Gender.MALE) {
            moods.addAll(List.of(
                    PreferredType.REALISTIC_SOCIAL,
                    PreferredType.PHILOSOPHICAL_REFLECTIVE
            ));

            genres.add(PreferredGenre.ECONOMY_MANAGEMENT);
        }

        return new RuleResult(genres, moods);
    }

    public List<Gender> convertSex(List<String> sexList) {
        if (sexList == null) return List.of();
        return sexList.stream()
                .map(String::toUpperCase)
                .map(s -> switch (s) {
                    case "MALE", "남", "남성" -> Gender.MALE;
                    case "FEMALE", "여", "여성" -> Gender.FEMALE;
                    default -> null;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public List<AgeGroup> convertAge(List<String> ageList) {
        if (ageList == null) return List.of();
        return ageList.stream()
                .map(String::toUpperCase)
                .map(s -> switch (s) {
                    case "10", "10대", "TEENS" -> AgeGroup.TEENS;
                    case "20", "20대", "TWENTIES" -> AgeGroup.TWENTIES;
                    case "30", "30대", "THIRTIES" -> AgeGroup.THIRTIES;
                    default -> AgeGroup.FORTY_PLUS;
                })
                .toList();
    }

    public List<Relation> convertRelationship(List<String> relList) {
        if (relList == null) return List.of();
        return relList.stream()
                .map(String::toUpperCase)
                .map(s -> switch (s) {
                    case "FRIEND", "친구" -> Relation.FRIEND;
                    case "LOVER", "연인" -> Relation.LOVER;
                    case "FAMILY", "가족" -> Relation.FAMILY;
                    case "COLLEAGUE", "직장동료" -> Relation.COLLEAGUE;
                    default -> null;
                })
                .filter(Objects::nonNull)
                .toList();
    }

}

