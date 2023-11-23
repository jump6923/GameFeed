package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Comment;
import java.time.LocalDateTime;

public class CommentResponseDto {
    private String content;
    private String userId;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
        this.modifiedAt = comment.getModifiedAt();
    }
}
