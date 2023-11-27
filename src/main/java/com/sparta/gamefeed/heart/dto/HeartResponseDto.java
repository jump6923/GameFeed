package com.sparta.gamefeed.heart.dto;

import lombok.Getter;

@Getter
public class HeartResponseDto {
    private boolean status;
    public HeartResponseDto(boolean status){
        this.status = status;
    }
}
