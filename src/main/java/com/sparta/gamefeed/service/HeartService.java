package com.sparta.gamefeed.service;

import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Heart;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.CommentRepository;
import com.sparta.gamefeed.repository.HeartRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;

    public void toggleHeartComment(Long commentId, User user) {

        // 커멘트가 있는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글이 없습니다.")));

        // 이미 하트를 눌렀는지 체크
        Optional<Heart> heart = heartRepository.findByUserAndComment(user, comment);

        if(heart.isPresent()){
            heartRepository.delete(heart.get());
            //comment.setLikeCount(comment);
        } else {

        }
    }
}
