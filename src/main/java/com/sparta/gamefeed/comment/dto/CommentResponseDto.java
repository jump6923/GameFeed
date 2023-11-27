package com.sparta.gamefeed.comment.dto;

import com.sparta.gamefeed.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String contents;
    private String username;
    private int likeCount;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.contents = comment.getContents();
        this.username = comment.getUser().getUsername();
        this.likeCount = comment.getLikeCount();
        this.modifiedAt = comment.getModifiedAt();
    }
}
