package com.sbungle.sbunglebe.inquiry.repository;

import com.sbungle.sbunglebe.inquiry.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByUserIdOrderByCreatedAtDesc(String userId);
}
