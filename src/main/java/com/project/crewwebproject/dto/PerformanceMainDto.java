package com.project.crewwebproject.dto;

import lombok.Builder;
import lombok.Getter;

//메인 페이지의 퍼포먼스 노출 데이터 전달
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
