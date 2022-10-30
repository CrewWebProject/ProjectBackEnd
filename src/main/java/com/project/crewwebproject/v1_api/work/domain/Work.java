package com.project.crewwebproject.v1_api.work.domain;

import com.project.crewwebproject.config.domain.Timestamped;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Work extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workId;

    @Column(nullable = false)
    private String workCategoryCode;

}
