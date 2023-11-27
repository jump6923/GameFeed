package com.sparta.gamefeed.categoryfolder.dto;

import com.sparta.gamefeed.categoryfolder.entity.Folder;
import lombok.Getter;

@Getter
public class FolderResponseDto {

    private String foldertitle;

    public FolderResponseDto(Folder folder) {
        this.foldertitle = folder.getFoldertitle();
    }
}
