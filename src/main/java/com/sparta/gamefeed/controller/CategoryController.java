package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.CategoryResponseDto;
import com.sparta.gamefeed.dto.FolderResponseDto;
import com.sparta.gamefeed.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/main")
    public List<CategoryResponseDto> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("/main/{categoryId}")
    public List<FolderResponseDto> getFolder(@PathVariable Long categoryId){
        return categoryService.getFolder(categoryId);
    }
}
