package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.CommentRequestDto;
import com.sparta.gamefeed.dto.CommentResponseDto;
import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto modifyComment(Long commentId, CommentRequestDto requestDto) {
        // comment가 있는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
        // 유저가 맞는지 확인
        // 수정
        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
        // Post가 있는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));
        // 생성
        Comment comment = new Comment(requestDto, post);
        // 저장
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> getComments(Long postId) {
        // 해당 Post가 있는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));

        List<Comment> commentList = commentRepository.findAllByPost_Id(postId); // 작동하는지 확인
        List<CommentResponseDto> responseDtoList = commentList.stream()
                .map(CommentResponseDto::new).toList();
        return responseDtoList;
    }
}
