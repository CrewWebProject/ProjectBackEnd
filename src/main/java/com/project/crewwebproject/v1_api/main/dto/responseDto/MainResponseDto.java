package com.project.crewwebproject.v1_api.main.dto.responseDto;

public class MainResponseDto {

    private String videoUrl;

    public MainResponseDto(String videoUrl) {

        this.videoUrl = videoUrl;

    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
