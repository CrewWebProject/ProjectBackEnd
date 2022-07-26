package com.project.crewwebproject.service;

import com.project.crewwebproject.domain.Performance;
import com.project.crewwebproject.domain.PerformanceImage;
import com.project.crewwebproject.dto.*;
import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.repository.PerformanceImageRepository;
import com.project.crewwebproject.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    private final PerformanceImageRepository performanceImageRepository;
    private final AwsS3Service awsS3Service;
    //퍼포먼스 저장기능
    @Transactional
    public List<Performance> savePerformance(PerformanceSaveDto performanceSaveDto, PerformanceImageSaveDto performanceImageSaveDto, PerformanceThumbnailSaveDto performanceThumbnailSaveDto){
        Performance performance = new Performance();
        performance.performanceSave(performanceSaveDto);

        List<String> imgThumbnail = awsS3Service.uploadFiles(performanceThumbnailSaveDto.getThumbnail());
        performance.setPerformanceThumbnail(imgThumbnail.get(0));

        Performance savePerformance = performanceRepository.save(performance);

        PerformanceImage performanceImage = new PerformanceImage();

        List<String> imgUrl = awsS3Service.uploadFiles(performanceImageSaveDto.getPerformaceImages());

        for (String url : imgUrl) {
            performanceImage.performanceImgSave(url);
            performanceImage.performanceImgPerformanceSave(savePerformance);
            performanceImageRepository.save(performanceImage);
        }

        return performanceRepository.findAll();
    }
    //퍼포먼스 페이지의 메인부분 노출
    @Transactional
    public List<PerformanceDto> getMainPerformance(){
        List<Performance> performanceList = performanceRepository.findAll();

        List<PerformanceDto> performanceDtoList = new ArrayList<>();

        for (Performance performance : performanceList) {
            PerformanceDto performanceDto = PerformanceDto.builder()
                    .performanceId(performance.getPerformanceId())
                    .mainTitle(performance.getMainTitle())
                    .subTitle(performance.getSubTitle())
                    .build();

            performanceDtoList.add(performanceDto);
        }

        return performanceDtoList;
    }

    //퍼포먼스 상세보기 리턴부분
    @Transactional
    public PerformanceDetailDto getDetailPerformance(Long performanceId){
        Performance performance = performanceRepository.findById(performanceId).orElseThrow(
                () -> new PrivateException(StatusCode.INTERNAL_SERVER_ERROR_PLZ_CHECK));

        List<PerformanceImage> performaceImages = performance.getPerformaceImages();
        List<String> performaceImagesUrl = new ArrayList<>();

        PerformanceDetailDto performanceDetailDto =  new PerformanceDetailDto();
        performanceDetailDto.setPerformanceDetailDto(performance);

        for (PerformanceImage performaceImage : performaceImages) {
            performaceImagesUrl.add(performaceImage.getPerformaceImage());
        }

        performanceDetailDto.setPerformanceImages(performaceImagesUrl);

        return performanceDetailDto;
    }
}
