package com.project.crewwebproject.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter

public class PerformanceImage {

    @Id
    private String performaceImageId;

    private String performaceImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id" ,nullable = false)
    private Performance performance;

    public void performanceImgPerformanceSave(Performance performance){
        this.performance = performance;
    }

    public void performanceImgSave(String performaceImage){
        this.performaceImage = performaceImage;
    }

}
