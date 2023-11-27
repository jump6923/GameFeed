package com.sparta.gamefeed.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    String username;
    String password;
}
