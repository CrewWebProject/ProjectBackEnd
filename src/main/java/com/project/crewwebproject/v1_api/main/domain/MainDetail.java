package com.project.crewwebproject.v1_api.main.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class MainDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainDetailId;

    //자식 요소가 부모 요소를 FK로 가진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_id" ,nullable = false)
    private Main main;

}
