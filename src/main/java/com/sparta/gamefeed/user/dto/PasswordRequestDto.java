package com.sparta.gamefeed.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequestDto {
    private String originPassword;
    private String changePassword;
}
