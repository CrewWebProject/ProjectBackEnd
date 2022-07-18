package com.project.crewwebproject.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MainVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mainVideoId;

    @Column
    private String mainVideoUrl;

    public void setMainVideoUrl(String videoUrl) {
        this.mainVideoUrl = videoUrl;
    }
}
