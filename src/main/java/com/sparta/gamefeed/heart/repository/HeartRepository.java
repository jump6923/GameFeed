package com.sparta.gamefeed.heart.repository;

import com.sparta.gamefeed.comment.entity.Comment;
import com.sparta.gamefeed.heart.entity.Heart;
import com.sparta.gamefeed.post.entity.Post;
import com.sparta.gamefeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByUserAndComment(User user, Comment comment);

    Heart findByUserAndPost(User user, Post post);
}
