package com.project.crewwebproject.controller;

import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/api/test/ok")
    public ResponseEntity<PrivateResponseBody> getStatusOk(){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , null), HttpStatus.OK);
    }

    @GetMapping("/api/test/getERR")
    public void getError(){
        throw new PrivateException(StatusCode.INTERNAL_SERVER_ERROR_PLZ_CHECK);
    }

}
