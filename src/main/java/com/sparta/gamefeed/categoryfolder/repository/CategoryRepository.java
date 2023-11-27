package com.sparta.gamefeed.categoryfolder.repository;

import com.sparta.gamefeed.categoryfolder.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
