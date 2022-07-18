package com.project.crewwebproject.dto;


import lombok.Data;

public class JwtTokenDto {
    private String accessTokenDto;

    public String getAccessTokenDto() {
        return accessTokenDto;
    }

    public JwtTokenDto(String accessTokenDto){
        this.accessTokenDto = accessTokenDto;
    }
}
