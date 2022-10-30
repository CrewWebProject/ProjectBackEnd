package com.project.crewwebproject.v1_api.performance.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Performance{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long performanceId;

    //퍼포먼스 메인 타이틀
    @Column(nullable = false , length = 255)
    private String performanceMainTitle;

    //퍼포먼스 서브 타이틀
    @Column(nullable = false , length = 255)
    private String performanceSubTitle;

}
