package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllById(long id);
//    Post findByContentsId(long userId);
//    Optional<Post> findByUsernameAndId(String username, long id);

}
