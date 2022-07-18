package com.project.crewwebproject.dto;


public class JwtTokenDto {
    private String accessToken;

    public JwtTokenDto(String accessTokenDto){
        this.accessToken = accessTokenDto;
    }
}
