package com.sparta.gamefeed.categoryfolder.service;

import com.sparta.gamefeed.categoryfolder.dto.CategoryResponseDto;
import com.sparta.gamefeed.categoryfolder.dto.FolderResponseDto;
import com.sparta.gamefeed.categoryfolder.entity.Category;
import com.sparta.gamefeed.categoryfolder.entity.CategoryFolder;
import com.sparta.gamefeed.categoryfolder.entity.Folder;
import com.sparta.gamefeed.categoryfolder.repository.CategoryFolderRepository;
import com.sparta.gamefeed.categoryfolder.repository.CategoryRepository;
import com.sparta.gamefeed.categoryfolder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryFolderRepository categoryFolderRepository;
    private final FolderRepository folderRepository;

    public List<CategoryResponseDto> getCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> responseDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            responseDtoList.add(new CategoryResponseDto(category));
        }

        return responseDtoList;
    }

    public List<FolderResponseDto> getFolder(Long id) {
        List<CategoryFolder> categoryFolderList = categoryFolderRepository.findAllByCategory_Id(id);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (CategoryFolder categoryFolder : categoryFolderList) {
            Folder folder = folderRepository.findById(categoryFolder.getFolder().getId()).orElseThrow(
                    () -> new IllegalArgumentException("해당 폴더가 존재하지 않습니다.")
            );

            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }
}
