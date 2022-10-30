package com.project.crewwebproject.auth.jwt.dto;


public class JwtTokenDto {
    private String accessToken;

    public JwtTokenDto(String accessTokenDto){
        this.accessToken = accessTokenDto;
    }
}
