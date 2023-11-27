package com.sparta.gamefeed.categoryfolder.dto;

import com.sparta.gamefeed.categoryfolder.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private String categorytitle;

    public CategoryResponseDto(Category category) {
        this.categorytitle = category.getCategorytitle();
    }
}
