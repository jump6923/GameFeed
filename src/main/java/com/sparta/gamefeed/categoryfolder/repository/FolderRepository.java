package com.sparta.gamefeed.categoryfolder.repository;

import com.sparta.gamefeed.categoryfolder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
