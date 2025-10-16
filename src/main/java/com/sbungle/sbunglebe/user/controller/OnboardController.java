package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.request.OnboardFirstRequest;
import com.sbungle.sbunglebe.user.dto.request.OnboardSecondRequest;
import com.sbungle.sbunglebe.user.dto.request.OnboardThirdRequest;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import com.sbungle.sbunglebe.user.service.OnboardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/onboard/v1")
@Slf4j
public class OnboardController {

    private final OnboardService onboardService;

    @Operation(summary = "온보딩 1 - 성별, 나이")
    @PostMapping("/first")
    public ResponseEntity<SimpleUserInfoResponse> firstStep(
            @AuthenticationPrincipal User currentUser,
            @Valid@RequestBody OnboardFirstRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("온보딩 첫번째 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updateGenderAge(userId, request.gender(), request.age());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "온보딩 2 - 좋아하는 장르")
    @PostMapping("/second")
    public ResponseEntity<SimpleUserInfoResponse> secondStep(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody OnboardSecondRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("온보딩 두번째 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updatePreferredGenre(userId, request.preferredGenres());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "온보딩 3 - 취향")
    @PostMapping("/third")
    public ResponseEntity<SimpleUserInfoResponse> thirdStep(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody OnboardThirdRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("온보딩 세번째 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updatePreferredType(userId, request.preferredTypes());
        return ResponseEntity.ok(response);
    }


}
