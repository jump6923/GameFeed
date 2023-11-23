package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.CategoryFolder;
import com.sparta.gamefeed.entity.Folder;
import com.sparta.gamefeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryFolderRepository extends JpaRepository<CategoryFolder, Long> {
    List<CategoryFolder> findAllByCategory_Id(Long id);
}
