package com.project.crewwebproject.v1_api.user.dto.requestDto;


public class UserSignUpRequestDto {
    private String userId;
    private String userName;
    private String userPassword;

    public UserSignUpRequestDto(String userId, String userName , String userPassword) {
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
