package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.CommentRequestDto;
import com.sparta.gamefeed.dto.CommentResponseDto;
import com.sparta.gamefeed.dto.StatusResponseDto;
import com.sparta.gamefeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postsId}")
    public ResponseEntity<?> createComment(@PathVariable Long postId,
                                           @RequestBody CommentRequestDto requestDto) {
        try {
            CommentResponseDto responseDto = commentService.createComment(postId, requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> modifyComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return null;
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {

        return null;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment() {

        return null;
    }
}
