package com.project.crewwebproject.v1_api.performance.controller;

import com.project.crewwebproject.v1_api.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

//    @ApiOperation("GET 프로젝트 페이지의 메인에 노출될 데이터들")
//    @GetMapping("/main")
//    public ResponseEntity<PrivateResponseBody> getMainPerformance(){
//        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.getMainPerformance()), HttpStatus.OK);
//    }
//    @ApiOperation("GET 프로젝트를 클릭해서 펼치기했을 때 노출될 프로젝트 상세 데이터들")
//    @GetMapping("/detail")
//    public ResponseEntity<PrivateResponseBody> getDetailPerformance(@RequestParam Long performanceId){
//        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.getDetailPerformance(performanceId)), HttpStatus.OK);
//    }
//
//    @ApiOperation("POST 관리자 페이지의 퍼포먼스 등록")
//    @PostMapping("/create")
//    public ResponseEntity<PrivateResponseBody> postPerformance(@RequestBody PerformanceSaveRequestDto performanceSaveRequestDto, PerformanceImageSaveRequestDto performanceImageSaveRequestDto, PerformanceThumbnailSaveRequestDto performanceThumbnailSaveRequestDto){
//        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.savePerformance(performanceSaveRequestDto, performanceImageSaveRequestDto, performanceThumbnailSaveRequestDto)), HttpStatus.OK);
//    }

}
