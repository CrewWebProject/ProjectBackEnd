package com.project.crewwebproject.controllerTest;

import com.project.crewwebproject.test.TestController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ActiveProfiles("local")
//Mockito의 Mock 객체를 사용하기 위한 어노테이션으로 Test class 위에 작성
@ExtendWith(MockitoExtension.class)
public class TestControllerTest {

    //@Mock 객체를 주입할 주체 객체로 선언
    @InjectMocks
    private TestController testController;

    private MockMvc mvc;

    @BeforeEach
    void initEach() {
        this.mvc = standaloneSetup(testController).addFilter(new CharacterEncodingFilter("UTF-8", true)).build();
    }

    @Test
    @Order(0)
    @DisplayName("테스트 정상 시작 알림")
    public void startTest() {
        System.out.println("Test 실행");
    }

    @Nested
    @DisplayName("메서드 : Get , URI : /api/test/ok")
    class GetHttpStatusOkTest {
        @Test
        @Order(1)
        @DisplayName("Case : Red")
        public void getHttpStatusOkTestCaseRed() throws Exception {

            MvcResult mvcResult = mvc.perform(get("/api/test/ok"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(jsonPath("statusCode", "999").exists())
                    .andExpect(jsonPath("statusMsg", "알수없는 서버 내부 에러 발생 , dladlsgur3334@gmail.com 으로 연락 부탁드립니다.").exists())
                    .andExpect(status().isInternalServerError())
                    .andReturn();

            MockHttpServletResponse response = mvcResult.getResponse();
            System.out.printf("response = ", response.getContentAsString());
        }

        @Test
        @Order(2)
        @DisplayName("Case : Green")
        public void getHttpStatusOkTestCaseGreen() throws Exception {

            MvcResult mvcResult = mvc.perform(get("/api/test/ok"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(jsonPath("statusCode", "0").exists())
                    .andExpect(jsonPath("statusMsg", "응답이 정상 처리 되었습니다.").exists())
                    .andExpect(status().isOk())
                    .andReturn();

            MockHttpServletResponse response = mvcResult.getResponse();
            System.out.printf("response = ", response.getContentAsString());
        }
    }

    @Nested
    @DisplayName("메서드 : Get , URI : /api/test/getERR")
    class GetErrorTest {
        @Test
        @Order(1)
        @DisplayName("Case : Red")
        public void getErrorTestCaseRed(){

            NestedServletException nestedServletException = Assertions.assertThrows(NestedServletException.class, () -> {
                mvc.perform(get("/api/test/getERR"));
            });

            Assertions.assertEquals(nestedServletException.getMessage(),
                    "Request processing failed; nested exception is " +
                            "com.project.crewwebproject.exception.PrivateException: " +
                            "응답이 정상 처리 되었습니다.");
        }

        @Test
        @Order(2)
        @DisplayName("Case : Green")
        public void getErrorTestCaseGreen() {

            NestedServletException nestedServletException = Assertions.assertThrows(NestedServletException.class, () -> {
                mvc.perform(get("/api/test/getERR"));
            });

            Assertions.assertEquals(nestedServletException.getMessage(),
                    "Request processing failed; nested exception is " +
                            "com.project.crewwebproject.exception.PrivateException: " +
                            "알수없는 서버 내부 에러 발생 , dladlsgur3334@gmail.com 으로 연락 부탁드립니다.");

        }
    }

//    @Nested
//    @DisplayName("메서드 : Get , URI : /api/logintest")
//    class LoginTest {
//        @Test
//        @Order(1)
//        @DisplayName("Case : Red")
//        public void loginTestCaseRed() {
//
//        }
//        @Test
//        @Order(2)
//        @DisplayName("Case : Green")
//        public void loginTestCaseGreen() {
//
//        }
//    }


}
