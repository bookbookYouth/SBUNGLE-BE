package com.sbungle.sbunglebe.domain.book.dto.vo;

import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;

import java.util.Set;

public record RuleResult(
        Set<PreferredGenre> genres,
        Set<PreferredType> moods
) {
}
