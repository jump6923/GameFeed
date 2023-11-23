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

    //특정 게시판 게시물 조회
    @GetMapping("/posts/category/folder/{categoryfolderId}")
    public List<PostResponseDto> getPostList(@PathVariable Long categoryfolderId) {
        return postService.getPostList(categoryfolderId);
    }

    //특정 게시물 조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
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

    //게시물 수정
    @PatchMapping("/posts/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId,
                                      @RequestBody PostRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, requestDto, userDetails.getUser().getId());
    }

    //게시물 삭제
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}
