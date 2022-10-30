package com.project.crewwebproject.v1_api.main.service;

import com.project.crewwebproject.v1_api.main.repository.MainRepository;
import com.project.crewwebproject.v1_api.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;

    private final PerformanceRepository performanceRepository;

    // TODO: request 보내주는 yotubeLink를 크롤링 -> S3에 저장 -> S3에 저장된 객체 주소 리턴
//    @Transactional
//    public MainResponseDto getMainVideoUrl() {
//
//    }

    // TODO: 기존 링크를 삭제 -> request 보내주는 yotubeLink를 크롤링 -> S3에 저장 -> S3에 저장된 객체 주소 리턴
//    @Transactional
//    public MainResponseDto postMainVideoUrl(MainResponseDto mainResponseDto) {
//
//    }

}
