package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
