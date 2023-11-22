package com.sparta.gamefeed.dto;


import com.sparta.gamefeed.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResponseDto {
    private String userId;
    private String email;
    private String introduce;

    public ProfileResponseDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.introduce = user.getIntroduce();
    }
}
