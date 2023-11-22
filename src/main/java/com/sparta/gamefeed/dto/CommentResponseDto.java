package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Comment;

public class CommentResponseDto {
    private String content;

    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
    }
}
