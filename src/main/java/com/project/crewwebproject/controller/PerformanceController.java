package com.project.crewwebproject.controller;

import com.project.crewwebproject.dto.PerformanceDto;
import com.project.crewwebproject.dto.PerformanceImageSaveDto;
import com.project.crewwebproject.dto.PerformanceSaveDto;
import com.project.crewwebproject.dto.PerformanceThumbnailSaveDto;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.service.PerformanceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    @ApiOperation("POST 관리자 페이지의 퍼포먼스 등록")
    @PostMapping("/create")
    public ResponseEntity<PrivateResponseBody> postPerformance(@RequestBody PerformanceSaveDto performanceSaveDto , PerformanceImageSaveDto performanceImageSaveDto , PerformanceThumbnailSaveDto performanceThumbnailSaveDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.savePerformance(performanceSaveDto , performanceImageSaveDto , performanceThumbnailSaveDto)), HttpStatus.OK);
    }

    @ApiOperation("GET 프로젝트 페이지의 메인에 노출될 데이터들")
    @GetMapping("/main")
    public ResponseEntity<PrivateResponseBody> getMainPerformance(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.getMainPerformance()), HttpStatus.OK);
    }

    @ApiOperation("GET 프로젝트를 클릭해서 펼치기했을 때 노출될 프로젝트 상세 데이터들")
    @GetMapping("/detail")
    public ResponseEntity<PrivateResponseBody> getDetailPerformance(@RequestParam Long performanceId){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.getDetailPerformance(performanceId)), HttpStatus.OK);
    }

}
