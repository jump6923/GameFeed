package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private String categorytitle;

    public CategoryResponseDto(Category category) {
        this.categorytitle = category.getCategorytitle();
    }
}
