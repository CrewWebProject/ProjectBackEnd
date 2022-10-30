package com.project.crewwebproject.v1_api.work.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class WorkDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workDetailId;

    @Column(nullable = false)
    private String workName;
    // 후원
    @Column(nullable = false)
    private String workSupported;
    // 협력
    @Column(nullable = false)
    private String workCooperation;
    // 일시
    @Column(nullable = false)
    private String workDateTime;
    // 송출
    @Column(nullable = false)
    private String workStreaming;
    // 프로그램
    @Column(nullable = false)
    private String workProgram;

    //자식 요소가 부모 요소를 FK로 가진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id" ,nullable = false)
    private Work work;

}
