package com.project.crewwebproject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ActiveProfiles("local")
@SpringBootTest
class CrewWebProjectApplicationTests {

    @Test
    @Order(0)
    @DisplayName("테스트 정상 시작 알림")
    public void startTest(){
        System.out.println("Test 실행");
    }

    @Test
    @Order(1)
    @DisplayName("크롤링 테스트")
    public void crollingTest(){
        List<WorkMainResponseDto> supportProjectWorkMainResponseDtos = new ArrayList<>();

        // 크롤링 Path
        String  CREW_BLOG_URL = "https://blog.naver.com/PostList.naver?blogId=caycrewbboy&from=postList&categoryNo=49";
        // 크롤링 Path에 connetion
        Connection conn = Jsoup.connect(CREW_BLOG_URL);
        // 크롤링
        try {
            // 커넥션으로부터 html 코드 get
            Document document = conn.get();
            // 해당 커넥션의 크롤링할 class 지정
            Elements imageUrlElements = document.getElementsByClass("thumb");

            Elements imageTitleElements = document.getElementsByClass("title ell");

            for (Element imageTitleElement : imageTitleElements) {
                WorkMainResponseDto workMainResponseDto = new WorkMainResponseDto();

                String imgUrl = imageTitleElement.toString().replace("<strong class=\"title ell\">", " ").replace("</strong>", " ");
                workMainResponseDto.setWorkThumbnailImageUrl(imgUrl);
                supportProjectWorkMainResponseDtos.add(workMainResponseDto);
            }

            for (int i = 0; i < supportProjectWorkMainResponseDtos.size() ; i++){
                String test = imageUrlElements.get(i).toString().replace("<img src=\"" , " ").replace("alt=\"\" onerror=\"this.onerror=null;this.src='https://ssl.pstatic.net/static/blog/no_image2.svg'\" class=\"thumb\">" , " ");
                supportProjectWorkMainResponseDtos.get(i).setWorkDescription(test);
            }

            for (WorkMainResponseDto test : supportProjectWorkMainResponseDtos) {
                System.out.println(test.getWorkDescription());
                System.out.println(test.getWorkThumbnailImageUrl());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
