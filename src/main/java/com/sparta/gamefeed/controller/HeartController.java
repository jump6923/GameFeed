package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.StatusResponseDto;
import com.sparta.gamefeed.security.UserDetailsImpl;
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
    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/heart")
    public ResponseEntity<StatusResponseDto> insertHeart(@PathVariable Long commentId,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(new StatusResponseDto("d", HttpStatus.OK.value()));
    }
}