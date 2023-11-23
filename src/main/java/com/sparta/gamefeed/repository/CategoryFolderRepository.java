package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.CategoryFolder;
import com.sparta.gamefeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryFolderRepository extends JpaRepository<CategoryFolder, Long> {
}
