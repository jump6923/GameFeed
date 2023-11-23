package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Comment;
import lombok.Getter;
import org.hibernate.annotations.Comments;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String contents;
    private String username;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.contents = comment.getContents();
        this.username = comment.getUser().getUsername();
        this.modifiedAt = comment.getModifiedAt();
    }
}
