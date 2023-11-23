package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
