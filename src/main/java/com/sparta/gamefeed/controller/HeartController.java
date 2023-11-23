package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.StatusResponseDto;
import com.sparta.gamefeed.entity.Heart;
import com.sparta.gamefeed.security.UserDetailsImpl;
import com.sparta.gamefeed.service.CommentService;
import com.sparta.gamefeed.service.HeartService;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;
    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/heart")
    public ResponseEntity<StatusResponseDto> toggleHeartComment(@PathVariable Long commentId,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            heartService.toggleHeartComment(commentId, userDetails.getUser());
            return ResponseEntity.ok().body(new StatusResponseDto("좋아요 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}