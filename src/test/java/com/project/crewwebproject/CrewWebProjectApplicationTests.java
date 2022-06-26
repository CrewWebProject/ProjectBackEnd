package com.project.crewwebproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CrewWebProjectApplicationTests {

    @Test
    @Order(0)
    @DisplayName("테스트 정상 시작 알림")
    public void startTest(){
        System.out.println("Test 실행");
    }
}
