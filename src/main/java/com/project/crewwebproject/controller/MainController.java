package com.project.crewwebproject.controller;

import com.project.crewwebproject.dto.MainVideoDto;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @GetMapping
    public ResponseEntity<PrivateResponseBody> getMainVideo(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , mainService.getMainVideoUrl()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrivateResponseBody> postMainVideo(MainVideoDto mainVideoDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , mainService.postMainVideoUrl(mainVideoDto)), HttpStatus.OK);
    }
}
