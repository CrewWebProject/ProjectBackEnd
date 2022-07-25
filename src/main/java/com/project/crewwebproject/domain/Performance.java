package com.project.crewwebproject.domain;

import com.project.crewwebproject.dto.PerformanceDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Performance extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long performanceId;

    //퍼포먼스 비디오 url
    @Column(nullable = false)
    private String performanceVideoUrl;

    //썸네일 이미지
    @Column(nullable = false)
    private String thumbnail;

    //퍼포먼스 메인 타이틀
    @Column(nullable = false)
    private String mainTitle;

    //퍼포먼스 서브 타이틀
    @Column(nullable = false)
    private String subTitle;

    //퍼포먼스 소개
    @Column(nullable = false)
    private String introduce;

    //퍼포먼스 시간
    @Column(nullable = false)
    private String time;

    //출연 인원
    @Column(nullable = false)
    private String personnel;

    public void setPerformance(PerformanceDto performanceDto){
        this.performanceVideoUrl = performanceDto.getPerformanceVideoUrl();
        this.mainTitle = performanceDto.getMainTitle();
        this.subTitle = performanceDto.getSubTitle();
        this.introduce = performanceDto.getIntroduce();
    }
}
