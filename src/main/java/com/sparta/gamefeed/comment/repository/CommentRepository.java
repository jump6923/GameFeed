package com.sparta.gamefeed.comment.repository;

import com.sparta.gamefeed.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost_IdOrderByCreatedAtDesc(Long postId);
}
