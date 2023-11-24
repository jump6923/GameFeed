package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private int likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto (Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
