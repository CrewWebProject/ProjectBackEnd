package com.project.crewwebproject.controller;

import com.project.crewwebproject.dto.MainVideoDto;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.service.MainService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @ApiOperation("GET 메인 페이지에 재생될 비디오 데이터")
    @GetMapping("/video")
    public ResponseEntity<PrivateResponseBody> getMainVideo(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , mainService.getMainVideoUrl()), HttpStatus.OK);
    }

    @ApiOperation("POST 메인 페이지에 재생될 비디오 데이터")
    @PostMapping("/video")
    public ResponseEntity<PrivateResponseBody> postMainVideo(MainVideoDto mainVideoDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , mainService.postMainVideoUrl(mainVideoDto)), HttpStatus.OK);
    }

    @ApiOperation("GET 메인 페이지에 노출될 퍼포먼스 데이터들")
    @GetMapping("/performance")
    public ResponseEntity<PrivateResponseBody> getMainPerformance(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , mainService.getMainPerformance()), HttpStatus.OK);
    }
}
