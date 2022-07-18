package com.project.crewwebproject.dto;


public class UserDto {

    private String userId;
    private String userName;
    private String userPassword;

    public UserDto(String userId, String userName ,String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {return userName; }

}
