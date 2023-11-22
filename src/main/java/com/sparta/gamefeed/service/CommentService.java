package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.CommentRequestDto;
import com.sparta.gamefeed.dto.CommentResponseDto;
import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
        // Post가 있는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post id 입니다."));
        // 생성
        Comment comment = new Comment(requestDto);
        comment.setPost(post);
        // 저장
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
