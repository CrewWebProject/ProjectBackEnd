package com.project.crewwebproject.controller;

import com.project.crewwebproject.dto.UserDto;
import com.project.crewwebproject.exception.PrivateResponseBody;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<PrivateResponseBody> login(UserDto userDto){
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , userService.login(userDto)), HttpStatus.OK);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<PrivateResponseBody> signUp(UserDto userDto){
        userService.signup(userDto);
        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , null), HttpStatus.OK);
    }

}
