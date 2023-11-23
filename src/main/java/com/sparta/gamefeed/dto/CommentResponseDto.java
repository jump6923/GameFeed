package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Comment;
import java.time.LocalDateTime;

public class CommentResponseDto {
    private String content;
    private String username;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.modifiedAt = comment.getModifiedAt();
    }
}
