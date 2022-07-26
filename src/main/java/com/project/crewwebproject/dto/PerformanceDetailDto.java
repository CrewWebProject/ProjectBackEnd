package com.project.crewwebproject.dto;

import com.project.crewwebproject.domain.Performance;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
public class PerformanceDetailDto {

    private Long performanceId;

    //퍼포먼스 메인 타이틀
    private String mainTitle;

    //퍼포먼스 서브 타이틀
    private String subTitle;

    //퍼포먼스 소개
    private String introduce;

    //출연 인원
    private String personnel;

    //퍼포먼스 런타임
    private String runTime;

    //퍼포먼스 비디오 url
    private String performanceVideoUrl;

    //퍼포먼스 이미지
    private List<String> performanceImages;

    //썸네일 이미지
    private String thumbnail;

    public void setPerformanceDetailDto(Performance performance) {
        this.performanceId = performance.getPerformanceId();
        this.mainTitle = performance.getMainTitle();
        this.subTitle = performance.getSubTitle();
        this.introduce = performance.getIntroduce();
        this.personnel = performance.getPersonnel();
        this.runTime = performance.getRunTime();
        this.performanceVideoUrl = performance.getPerformanceVideoUrl();
        this.thumbnail = performance.getThumbnail();
    }

}
