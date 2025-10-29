package com.sbungle.sbunglebe.inquiry.controller;

import com.sbungle.sbunglebe.inquiry.dto.request.CreateInquiryRequest;
import com.sbungle.sbunglebe.inquiry.dto.response.InquiryResponse;
import com.sbungle.sbunglebe.inquiry.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inquiry/v1")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "문의하기 생성")
    @PostMapping
    public ResponseEntity<InquiryResponse> createInquiry(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody CreateInquiryRequest request
    ) {
        String userId = currentUser.getUsername();
        InquiryResponse response = inquiryService.createInquiry(userId, request.content());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "내 문의 목록 조회")
    @GetMapping
    public ResponseEntity<List<InquiryResponse>> getInquiryList(
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();
        List<InquiryResponse> responses = inquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.ok(responses);
    }
}
