package com.sbungle.sbunglebe.user.repository;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferredGenreRepository extends JpaRepository<UserPreferredGenre, Long> {
    void deleteAllByUser(UserEntity user);
}
