package com.project.crewwebproject.v1_api.main.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class MainMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainMetaId;

    @Column(nullable = false , length = 255)
    private String mainVideoUrl;

    //자식 요소가 부모 요소를 FK로 가진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_detail_id" ,nullable = false)
    private MainDetail mainDetail;

}
