package com.sparta.gamefeed.heart.controller;

import com.sparta.gamefeed.heart.dto.HeartResponseDto;
import com.sparta.gamefeed.common.dto.StatusResponseDto;
import com.sparta.gamefeed.security.UserDetailsImpl;
import com.sparta.gamefeed.heart.service.HeartService;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;

    // 댓글 좋아요 눌렀는지 여부 가져오기
    @GetMapping("/comments/{commentId}/heart")
    public ResponseEntity<?> getHeartStatusComment(@PathVariable Long commentId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            HeartResponseDto responseDto = heartService.checkHeartComment(commentId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/heart")
    public ResponseEntity<?> toggleHeartComment(@PathVariable Long commentId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            HeartResponseDto responseDto = heartService.toggleHeartComment(commentId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글 좋아요 눌렀는지 여부 가져오기
    @GetMapping("/posts/{postId}/heart")
    public ResponseEntity<?> getHeartStatusPost(@PathVariable Long postId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            HeartResponseDto responseDto = heartService.checkHeartPost(postId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글 좋아요
    @PostMapping("/posts/{postId}/heart")
    public ResponseEntity<?> toggleHeartPost(@PathVariable Long postId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            HeartResponseDto responseDto = heartService.toggleHeartPost(postId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}