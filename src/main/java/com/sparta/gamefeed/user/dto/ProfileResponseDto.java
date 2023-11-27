package com.sparta.gamefeed.user.dto;


import com.sparta.gamefeed.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResponseDto {
    private String username;
    private String email;
    private String introduce;

    public ProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.introduce = user.getIntroduce();
    }
}
