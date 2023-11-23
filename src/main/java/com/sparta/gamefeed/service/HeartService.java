package com.sparta.gamefeed.service;

import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Heart;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.HeartRepository;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void toggleHeartComment(Long commentId, User user) {

        // 커멘트가 있는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));

        // 사용자와 댓글 작성자가 일치한지 체크
        if(user.getId().equals(comment.getUser().getId())){
            throw new RejectedExecutionException("본인의 댓글에는 좋아요를 누를 수 없습니다");
        }

        // 이미 하트를 눌렀는지 체크
        Optional<Heart> existingHeart = heartRepository.findByUserAndComment(user, comment);

        if(existingHeart.isPresent()){ // 이미 하트가 눌려있다면 삭제
            heartRepository.delete(existingHeart.get());
            comment.decreaseLikeCount();
        } else { // 아니면 생성
            Heart heart = new Heart();
            heart.setComment(comment);
            heart.setUser(user);
            heartRepository.save(heart);
            comment.increaseLikeCount();
        }
        commentRepository.save(comment);
    }
}
