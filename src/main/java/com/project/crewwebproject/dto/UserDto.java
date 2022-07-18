package com.project.crewwebproject.dto;


public class UserDto {

    private String userId;

    private String userPassword;

    public UserDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
