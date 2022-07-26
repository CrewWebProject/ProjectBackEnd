package com.project.crewwebproject.dto;

import lombok.Builder;
import lombok.Getter;

//퍼포먼스 페이지의 메인부분 노출 데이터 전달
@Getter
@Builder
public class PerformanceDto {

    private Long performanceId;

    private String mainTitle;

    private String subTitle;

    public PerformanceDto(Long performanceId, String mainTitle, String subTitle) {
        this.performanceId = performanceId;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
    }
}
