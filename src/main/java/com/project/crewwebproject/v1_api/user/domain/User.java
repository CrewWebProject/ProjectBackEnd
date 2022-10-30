package com.project.crewwebproject.v1_api.user.domain;

import com.project.crewwebproject.v1_api.user.dto.requestDto.UserSignUpRequestDto;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    public void signUp(UserSignUpRequestDto userSignUpRequestDto, String userPassword){
        this.userId = userSignUpRequestDto.getUserId();
        this.userName = userSignUpRequestDto.getUserName();
        this.userPassword = userPassword;
    }

}
