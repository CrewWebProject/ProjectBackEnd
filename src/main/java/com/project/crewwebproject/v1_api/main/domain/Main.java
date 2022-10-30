package com.project.crewwebproject.v1_api.main.domain;

import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class Main {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainId;

}
