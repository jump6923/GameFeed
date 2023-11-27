package com.sparta.gamefeed.categoryfolder.repository;

import com.sparta.gamefeed.categoryfolder.entity.CategoryFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryFolderRepository extends JpaRepository<CategoryFolder, Long> {
    List<CategoryFolder> findAllByCategory_Id(Long id);
}
