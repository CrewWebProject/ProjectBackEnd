package com.project.crewwebproject.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    @Id
    private String userId;

    @Column
    private String userName;

    @Column
    private Integer userPassword;

}
