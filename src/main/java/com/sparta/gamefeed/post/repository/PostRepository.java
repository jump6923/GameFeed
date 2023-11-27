package com.sparta.gamefeed.post.repository;

import com.sparta.gamefeed.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllById(long id);
//    Post findByContentsId(long userId);
//    Optional<Post> findByUsernameAndId(String username, long id);

}
