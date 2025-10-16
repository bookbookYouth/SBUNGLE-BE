package com.sbungle.sbunglebe.contact.repository;

import com.sbungle.sbunglebe.contact.domain.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContactRepository extends JpaRepository<UserContact, Long> {
    List<UserContact> findAllByUserIdOrderByCreatedAtDesc(String userId);
}
