package com.project.crewwebproject.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PerformanceMainDto {

    private Long performanceId;

    private String mainTitle;

    private String subTitle;

    private String thumbnail;

    private String introduce;

    public PerformanceMainDto(Long performanceId, String mainTitle, String subTitle, String thumbnail, String introduce) {
        this.performanceId = performanceId;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.thumbnail = thumbnail;
        this.introduce = introduce;
    }
}
