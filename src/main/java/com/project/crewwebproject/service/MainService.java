package com.project.crewwebproject.service;

import com.project.crewwebproject.domain.MainVideo;
import com.project.crewwebproject.dto.MainVideoDto;
import com.project.crewwebproject.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;

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

}
