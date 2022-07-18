package com.project.crewwebproject.controller;

import com.project.crewwebproject.dto.PerformanceDto;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    @PostMapping("performance/create")
    public ResponseEntity<PrivateResponseBody> postPerformance(@RequestBody PerformanceDto performanceDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.savePerformance(performanceDto)), HttpStatus.OK);
    }

    @GetMapping("performance/read")
    public ResponseEntity<PrivateResponseBody> getPerformance(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , performanceService.getPerformance()), HttpStatus.OK);
    }

}
