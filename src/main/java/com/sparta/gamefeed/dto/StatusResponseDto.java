package com.sparta.gamefeed.dto;

import lombok.Data;

@Data
public class StatusResponseDto {
    String message;
    int value;

    public StatusResponseDto(String message, int value) {
        this.message = message;
        this.value = value;
    }
}