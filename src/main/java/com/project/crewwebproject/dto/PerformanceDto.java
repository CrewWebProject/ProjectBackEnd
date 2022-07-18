package com.project.crewwebproject.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PerformanceDto {

    private Long performanceId;

    private String performanceVideoUrl;

    private String mainTitle;

    private String subTitle;

    private String introduce;

    public PerformanceDto(String performanceVideoUrl , String mainTitle, String subTitle, String introduce) {
        this.performanceVideoUrl = performanceVideoUrl;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.introduce = introduce;
    }

}
