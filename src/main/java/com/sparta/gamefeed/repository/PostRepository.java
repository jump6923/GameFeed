package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
