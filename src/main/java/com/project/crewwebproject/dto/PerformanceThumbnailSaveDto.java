package com.project.crewwebproject.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PerformanceThumbnailSaveDto {

    private List<MultipartFile> thumbnail;

    public List<MultipartFile> getThumbnail() {
        return thumbnail;
    }
}
