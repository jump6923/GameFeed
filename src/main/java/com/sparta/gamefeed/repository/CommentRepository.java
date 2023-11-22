package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
