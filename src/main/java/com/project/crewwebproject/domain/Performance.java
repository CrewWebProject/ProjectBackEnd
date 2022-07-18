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

    @Column(nullable = false)
    private String performanceVideoUrl;

    @Column(nullable = false)
    private String mainTitle;

    @Column(nullable = false)
    private String subTitle;

    @Column(nullable = false)
    private String introduce;

    public void setPerformance(PerformanceDto performanceDto){
        this.performanceVideoUrl = performanceDto.getPerformanceVideoUrl();
        this.mainTitle = performanceDto.getMainTitle();
        this.subTitle = performanceDto.getSubTitle();
        this.introduce = performanceDto.getIntroduce();
    }
}
