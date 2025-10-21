package com.sbungle.sbunglebe.user.repository;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferredTypeRepository extends JpaRepository<UserPreferredType, Long> {
    void deleteAllByUser(UserEntity user);
}
