package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.request.UpdateAgeRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdateGenderRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdatePreferredGenreRequest;
import com.sbungle.sbunglebe.user.dto.request.UpdatePreferredTypeRequest;
import com.sbungle.sbunglebe.user.dto.response.DetailUserInfoResponse;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import com.sbungle.sbunglebe.user.service.UserPreferenceService;
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
    private final UserPreferenceService userPreferenceService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<DetailUserInfoResponse> getUserInfo(
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();

        DetailUserInfoResponse response = userService.getUserDetailInfo(userId);
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

        SimpleUserInfoResponse response = userService.updateGender(userId, request.gender());
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

        SimpleUserInfoResponse response = userService.updateAge(userId, request.age());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 선호 장르 수정 - 기존 목록 제거")
    @PutMapping("/preferred-genres")
    public ResponseEntity<SimpleUserInfoResponse> updatePreferredGenre(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdatePreferredGenreRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 선호 장르 수정 userID: {}", userId);

        SimpleUserInfoResponse response = userPreferenceService.updatePreferredGenre(userId, request.preferredGenres());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 선호 타입 수정 - 기존 목록 제거")
    @PutMapping("/preferred-types")
    public ResponseEntity<SimpleUserInfoResponse> updatePreferredType(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody UpdatePreferredTypeRequest request
    ) {
        String userId = currentUser.getUsername();
        log.debug("유저 선호 타입 수정 userID: {}", userId);

        SimpleUserInfoResponse response = userPreferenceService.updatePreferredType(userId, request.preferredTypes());
        return ResponseEntity.ok(response);
    }


}
