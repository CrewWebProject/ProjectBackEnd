package com.project.crewwebproject.service;

import com.project.crewwebproject.domain.MainVideo;
import com.project.crewwebproject.domain.Performance;
import com.project.crewwebproject.dto.MainVideoDto;
import com.project.crewwebproject.dto.PerformanceDto;
import com.project.crewwebproject.dto.PerformanceMainDto;
import com.project.crewwebproject.repository.MainRepository;
import com.project.crewwebproject.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;

    private final PerformanceRepository performanceRepository;

    // TODO: request 보내주는 yotubeLink를 크롤링 -> S3에 저장 -> S3에 저장된 객체 주소 리턴
    @Transactional
    public MainVideoDto getMainVideoUrl() {

        List<MainVideo> mainVideoVideoUrlList = mainRepository.findAll();

        String videoUrl = mainVideoVideoUrlList.get(0).getMainVideoUrl();

        MainVideoDto mainVideoDto = new MainVideoDto(videoUrl);

        return mainVideoDto;
    }

    // TODO: 기존 링크를 삭제 -> request 보내주는 yotubeLink를 크롤링 -> S3에 저장 -> S3에 저장된 객체 주소 리턴
    @Transactional
    public MainVideoDto postMainVideoUrl(MainVideoDto mainVideoDto) {

        mainRepository.deleteAll();

        MainVideo mainVideo = new MainVideo();

        mainVideo.setMainVideoUrl(mainVideoDto.getVideoUrl());

        MainVideo saveVideo = mainRepository.save(mainVideo);

        mainVideo.setMainVideoUrl(saveVideo.getMainVideoUrl());

        return mainVideoDto;
    }
    //메인 페이지에 필요한 퍼포먼스 리스트
    @Transactional
    public List<PerformanceMainDto> getMainPerformance() {
        List<Performance> performanceList = performanceRepository.findAll();
        List<PerformanceMainDto> performanceDtoList = new ArrayList<>();

        for (Performance performance : performanceList) {
            PerformanceMainDto performanceMainDto = PerformanceMainDto.builder()
                    .performanceId(performance.getPerformanceId())
                    .mainTitle(performance.getMainTitle())
                    .subTitle(performance.getSubTitle())
                    .thumbnail(performance.getThumbnail())
                    .introduce(performance.getIntroduce())
                    .build();
            performanceDtoList.add(performanceMainDto);
        }
        return performanceDtoList;
    }
}
