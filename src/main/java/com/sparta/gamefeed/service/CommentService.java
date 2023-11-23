package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.CommentRequestDto;
import com.sparta.gamefeed.dto.CommentResponseDto;
import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
        // 해당 Post가 있는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));
        Comment comment = new Comment(requestDto, post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComments(Long postId) {
        // 해당 Post가 있는지 확인
        postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post 입니다."));

        List<Comment> commentList = commentRepository.findAllByPost_Id(postId); // 작동하는지 확인
        List<CommentResponseDto> responseDtoList = commentList.stream().map(CommentResponseDto::new).toList();
        return responseDtoList;
    }

    @Transactional
    public CommentResponseDto modifyComment(Long commentId, CommentRequestDto requestDto, User user) {
        // comment가 있는지 확인 + 이후 작성자가 맞는지도 확인할 예정
        Comment comment = getValidationComment(commentId, user);

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        // comment가 있는지 확인 + 이후 작성자가 맞는지도 확인할 예정
        Comment comment = getValidationComment(commentId, user);

        commentRepository.delete(comment);
    }

    public Comment getValidationComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
        if(!user.getId().equals(comment.getUser().getId())){
            throw new RejectedExecutionException("댓글 작성자만 삭제가 가능 합니다.");
        }
        return comment;
    }
}