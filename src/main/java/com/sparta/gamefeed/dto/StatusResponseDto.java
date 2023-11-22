package com.sparta.gamefeed.dto;

import lombok.Data;

@Data
public class StatusResponseDto {
    String message;
    int status;

    public StatusResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}