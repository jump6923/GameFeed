package com.sparta.gamefeed.dto;

import com.sparta.gamefeed.entity.Folder;
import lombok.Getter;

@Getter
public class FolderResponseDto {

    private String foldertitle;

    public FolderResponseDto(Folder folder) {
        this.foldertitle = folder.getFoldertitle();
    }
}
