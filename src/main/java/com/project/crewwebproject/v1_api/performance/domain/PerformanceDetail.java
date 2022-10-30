package com.project.crewwebproject.v1_api.performance.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PerformanceDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long performanceDetailId;

    //퍼포먼스 소개
    @Column(nullable = false , length = 255)
    private String performanceIntroduce;

    //퍼포먼스 시간
    @Column(nullable = false , length = 25)
    private String performanceRunTime;

    //출연 인원
    @Column(nullable = false , length = 25)
    private String performancePersonnel;

    //자식 요소가 부모 요소를 FK로 갖는다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id" ,nullable = false)
    private Performance performance;

}
