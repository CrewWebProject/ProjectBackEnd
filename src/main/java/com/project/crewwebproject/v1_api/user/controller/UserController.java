package com.project.crewwebproject.v1_api.user.controller;

import com.project.crewwebproject.config.exception.PrivateResponseBody;
import com.project.crewwebproject.config.exception.StatusCode;
import com.project.crewwebproject.v1_api.user.dto.requestDto.UserSignUpRequestDto;
import com.project.crewwebproject.v1_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<PrivateResponseBody> login(UserSignUpRequestDto userSignUpRequestDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , userService.login(userSignUpRequestDto)), HttpStatus.OK);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<PrivateResponseBody> signUp(UserSignUpRequestDto userSignUpRequestDto){
        userService.signup(userSignUpRequestDto);
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , null), HttpStatus.OK);
    }

}
