package com.sparta.gamefeed.entity;

import com.sparta.gamefeed.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "contents",nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryfolder_id")
    private CategoryFolder categoryFolder;

    public Post(PostRequestDto requestDto, User user, CategoryFolder categoryFolder) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
        this.categoryFolder = categoryFolder;
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
