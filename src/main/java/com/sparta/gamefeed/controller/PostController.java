package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.PostRequestDto;
import com.sparta.gamefeed.dto.PostResponseDto;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.security.UserDetailsImpl;
import com.sparta.gamefeed.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    //게시판 전체 게시물 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPostList() {
        return postService.getPostList();
    }

    //특정 게시판 게시물 조회
    @GetMapping("/posts/{categoryfolderId}")
    public List<PostResponseDto> getPostList(@PathVariable Long categoryfolderId) {
        return postService.getPostList(categoryfolderId);
    }

    //특정 게시물 조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    //게시물 등록
    @PostMapping("/posts/{categoryfolderId}")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @PathVariable Long categoryfolderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, categoryfolderId, userDetails.getUser().getUsername());
    }
    //게시물 수정
    @PatchMapping("/posts/{postId}")
    public PostResponseDto update(@PathVariable Long postId, @RequestBody PostRequestDto requestDto ) {
        return postService.update(postId, requestDto);
    }
    //게시물 삭제
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }


}
