package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.PostRequestDto;
import com.sparta.gamefeed.dto.PostResponseDto;
import com.sparta.gamefeed.dto.StatusResponseDto;
import com.sparta.gamefeed.security.UserDetailsImpl;
import com.sparta.gamefeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    //게시판 전체 게시물 조회 (ResponseEntity)
    @GetMapping("/posts/category")
    public ResponseEntity<?> getPostList() {
        try {
            List<PostResponseDto> responseDto = postService.getPostList();
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
    //특정 게시판 게시물 조회(ResponseEntity)

    @GetMapping("/posts/category/folder/{categoryfolderId}")
    public ResponseEntity<?> getPostList(@PathVariable Long categoryfolderId) {
        try {
            List<PostResponseDto> responseDto = postService.getPostList(categoryfolderId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //특정 게시물 조회(ResponseEntity)
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        try {
            PostResponseDto responseDto = postService.getPost(postId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시물 등록 (ResponseEntity)
    @PostMapping("/posts/{categoryfolderId}")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto requestDto,
                                        @PathVariable Long categoryfolderId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            PostResponseDto responseDto = postService.createPost(requestDto, categoryfolderId, userDetails.getUser().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시물 수정(ResponseEntity)
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestBody PostRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            PostResponseDto responseDto = postService.updatePost(postId, requestDto, userDetails.getUser().getId());
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시물 삭제(ResponseEntity)
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.deletePost(postId, userDetails.getUser().getId());
            return ResponseEntity.ok().body(new StatusResponseDto("게시물 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new StatusResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
