package com.project.crewwebproject.v1_api.work.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class WorkMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workMetaId;

    @Column(nullable = false)
    private String workImageUrl;

    //자식 요소가 부모 요소를 FK로 가진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_detail_id" ,nullable = false)
    private WorkDetail workDetail;
}
