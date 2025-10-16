package com.sbungle.sbunglebe.inquiry.service;

import com.sbungle.sbunglebe.inquiry.domain.Inquiry;
import com.sbungle.sbunglebe.inquiry.dto.response.InquiryResponse;
import com.sbungle.sbunglebe.inquiry.repository.InquiryRepository;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final UserService userService;
    private final InquiryRepository inquiryRepository;

    @Transactional
    public InquiryResponse createInquiry(String userId, String content) {
        userService.findUserByIdOrThrow(userId);

        Inquiry toSave = Inquiry.of(userId, content);
        inquiryRepository.save(toSave);
        return InquiryResponse.from(toSave);
    }

    @Transactional(readOnly = true)
    public List<InquiryResponse> getInquiriesByUserId(String userId) {
        return inquiryRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(InquiryResponse::from)
                .toList();
    }
}
