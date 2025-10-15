package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.request.UpdateAgeRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdateGenderRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdatePreferredGenreRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdatePreferredTypeRequest;
import com.sbungle.sbunglebe.user.dto.response.DetailUserInfoResponse;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import com.sbungle.sbunglebe.user.service.OnboardService;
import com.sbungle.sbunglebe.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
@Slf4j
public class UserController {

    private final UserService userService;
    private final OnboardService onboardService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<DetailUserInfoResponse> getUserInfo(
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();

        DetailUserInfoResponse response = userService.getUserInfo(userId);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "유저 성별 수정")
    @PatchMapping("/gender")
    public ResponseEntity<SimpleUserInfoResponse> updateGender(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdateGenderRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 성별 수정 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updateGender(userId, request.gender());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 나이 수정")
    @PatchMapping("/age")
    public ResponseEntity<SimpleUserInfoResponse> updateAge(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdateAgeRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 나이 수정 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updateAge(userId, request.age());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 선호 장르 수정")
    @PatchMapping("/preferred-genre")
    public ResponseEntity<SimpleUserInfoResponse> updatePreferredGenre(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdatePreferredGenreRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 선호 장르 수정 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updatePreferredGenre(userId, request.preferredGenre());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 선호 타입 수정")
    @PatchMapping("/preferred-type")
    public ResponseEntity<SimpleUserInfoResponse> updatePreferredType(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdatePreferredTypeRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 선호 타입 수정 userID: {}", userId);

        SimpleUserInfoResponse response = onboardService.updatePreferredType(userId, request.preferredType());
        return ResponseEntity.ok(response);
    }


}
