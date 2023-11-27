package com.sparta.gamefeed.post.entity;

import com.sparta.gamefeed.categoryfolder.entity.CategoryFolder;
import com.sparta.gamefeed.common.entity.Timestamped;
import com.sparta.gamefeed.user.entity.User;
import com.sparta.gamefeed.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @ColumnDefault("0")
    @Column(name = "heart")
    private int likeCount;

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

    //title, contents 수정내용이 null이면 기존 내용
    public void updatePost(PostRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContents() != null) {
            this.contents = requestDto.getContents();
        }
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        if (likeCount > 0) {
            likeCount--;
        }
    }
}
