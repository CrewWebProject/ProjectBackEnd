package com.project.crewwebproject.domain;

import com.project.crewwebproject.dto.PerformanceSaveDto;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Performance{

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
    private String runTime;

    //출연 인원
    @Column(nullable = false)
    private String personnel;

    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PerformanceImage> performaceImages;


    public void performanceSave(PerformanceSaveDto performanceSaveDto) {
        this.performanceVideoUrl = performanceSaveDto.getPerformanceVideoUrl();
        this.mainTitle = performanceSaveDto.getMainTitle();
        this.subTitle = performanceSaveDto.getSubTitle();
        this.introduce = performanceSaveDto.getIntroduce();
        this.runTime = performanceSaveDto.getRunTime();
        this.personnel = performanceSaveDto.getPersonnel();
    }

    public void setPerformanceThumbnail(String thumbnailUrl) {
        this.thumbnail = thumbnailUrl;
    }

}
