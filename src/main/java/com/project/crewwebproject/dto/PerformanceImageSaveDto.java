package com.project.crewwebproject.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PerformanceImageSaveDto {
    private List<MultipartFile> performaceImages;

    public List<MultipartFile> getPerformaceImages() {
        return performaceImages;
    }

}
