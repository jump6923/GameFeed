package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.HeartResponseDto;
import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Heart;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.HeartRepository;
import com.sparta.gamefeed.repository.PostRepository;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public HeartResponseDto checkHeartComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));
        Heart heart = heartRepository.findByUserAndComment(user,comment);
        return new HeartResponseDto(heart != null);
    }

    @Transactional
    public HeartResponseDto toggleHeartComment(Long commentId, User user) {

        // 커멘트가 있는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));

        // 사용자와 댓글 작성자가 일치한지 체크
        if (user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("본인의 댓글에는 좋아요를 누를 수 없습니다");
        }

        // 이미 하트를 눌렀는지 체크
        Heart existingHeart = heartRepository.findByUserAndComment(user, comment);

        // 이미 하트가 눌려있다면 삭제
        if (existingHeart != null) {
            heartRepository.delete(existingHeart);
            comment.decreaseLikeCount();
            return new HeartResponseDto(false);
        }

        Heart heart = new Heart(user, comment);
        heartRepository.save(heart);
        comment.increaseLikeCount();
        return new HeartResponseDto(true);
    }

    public HeartResponseDto checkHeartPost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));
        Heart heart = heartRepository.findByUserAndPost(user,post);
        return new HeartResponseDto(heart != null);
    }

    @Transactional
    public HeartResponseDto toggleHeartPost(Long postId, User user) {

        // 게시글이 있는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));

        // 사용자와 게시글 작성자가 일치한지 체크
        if (user.getId().equals(post.getUser().getId())) {
            throw new RejectedExecutionException("본인의 게시글에는 좋아요를 누를 수 없습니다");
        }

        // 이미 하트를 눌렀는지 체크
        Heart existingHeart = heartRepository.findByUserAndPost(user, post);

        // 이미 하트가 눌러져있다면 삭제
        if (existingHeart != null) {
            heartRepository.delete(existingHeart);
            post.decreaseLikeCount();
            return new HeartResponseDto(false);
        }

        Heart heart = new Heart(user,post);
        heartRepository.save(heart);
        post.increaseLikeCount();
        return new HeartResponseDto(true);
    }
}
