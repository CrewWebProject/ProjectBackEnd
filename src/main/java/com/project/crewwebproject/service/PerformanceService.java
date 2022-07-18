package com.project.crewwebproject.service;

import com.project.crewwebproject.domain.Performance;
import com.project.crewwebproject.dto.PerformanceDto;
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

    @Transactional
    public List<Performance> savePerformance(PerformanceDto performanceDto){
        Performance performance = new Performance();

        if (performanceDto.getPerformanceId() != null){
            performanceRepository.findById(performanceDto.getPerformanceId());
        }

        performance.setPerformance(performanceDto);

        performanceRepository.save(performance);

        return performanceRepository.findAll();
    }

    @Transactional
    public List<PerformanceDto> getPerformance(){
        List<Performance> performanceList = performanceRepository.findAll();

        List<PerformanceDto> performanceDtoList = new ArrayList<>();

        for (Performance performance : performanceList) {

            PerformanceDto performanceDto = PerformanceDto.builder()
                    .performanceId(performance.getPerformanceId())
                    .performanceVideoUrl(performance.getPerformanceVideoUrl())
                    .mainTitle(performance.getMainTitle())
                    .subTitle(performance.getSubTitle())
                    .introduce(performance.getIntroduce())
                    .build();

            performanceDtoList.add(performanceDto);

        }

        return performanceDtoList;
    }
}
