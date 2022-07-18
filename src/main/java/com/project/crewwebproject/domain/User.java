package com.project.crewwebproject.domain;

import com.project.crewwebproject.dto.UserDto;
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

    public void signUp(UserDto userDto , String userPassword){
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.userPassword = userPassword;
    }
}
