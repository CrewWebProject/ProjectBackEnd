package com.project.crewwebproject.v1_api.performance.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PerformanceMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String performanceMetaId;

    //퍼포먼스 이미지 url
    @Column(nullable = false , length = 255)
    private String performaceImage;

    //퍼포먼스 비디오 url
    @Column(nullable = false , length = 255)
    private String performanceVideoUrl;

    //자식 요소가 부모 요소를 FK로 갖는다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_detail_id" ,nullable = false)
    private PerformanceDetail performanceDetail;

}
