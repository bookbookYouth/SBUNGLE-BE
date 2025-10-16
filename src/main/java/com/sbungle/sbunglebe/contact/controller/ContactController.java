package com.sbungle.sbunglebe.contact.controller;

import com.sbungle.sbunglebe.contact.dto.request.CreateContactRequest;
import com.sbungle.sbunglebe.contact.dto.response.ContactResponse;
import com.sbungle.sbunglebe.contact.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact/v1")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "문의하기 생성")
    @PostMapping
    public ResponseEntity<ContactResponse> createContact(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody CreateContactRequest request
    ) {
        String userId = currentUser.getUsername();
        ContactResponse response = contactService.createContact(userId, request.content());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "내 문의 목록 조회")
    @GetMapping
    public ResponseEntity<List<ContactResponse>> getContactList(
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();
        List<ContactResponse> responses = contactService.getContactsByUserId(userId);
        return ResponseEntity.ok(responses);
    }
}
