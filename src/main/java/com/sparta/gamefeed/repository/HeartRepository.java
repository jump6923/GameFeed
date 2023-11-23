package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Comment;
import com.sparta.gamefeed.entity.Heart;
import com.sparta.gamefeed.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndComment(User user, Comment comment);
}
