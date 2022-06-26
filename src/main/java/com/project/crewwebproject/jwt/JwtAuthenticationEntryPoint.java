package com.project.crewwebproject.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
   //AuthenticationEntryPoint는 인증이 되지않은 유저가 요청을 했을때 동작된다.
   //인증과정에서 실패하거나 인증헤더(Authorization)를 보내지 않게되는 경우 예외를 처리하는 AuthenticationEntryPoint를 구현한다.

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {

      // Login 요청에서 Unauthorized 가 뜨면 비밀번호 가 틀린 경우
      // response.getWriter().print("text") : 리퀘스트를 보낸 곳으로 객체를 전달한다.;
      if (request.getRequestURI().startsWith("/user/login")) {
         //백엔드 측에 출력될 에러 메시지
         System.out.println("잘못된 비밀번호를 입력하셨습니다.");
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         PrintWriter writer = response.getWriter();
         writer.println("HTTP Status 401 / ErrCode : 111 , Msg : Plz check User's Password. Password is not Matching with DB Password");
      }

      // Login 요청을 제외하고 Unauthorized 가 뜨면 Token 이 없거나 잘못된 토큰을 가지고 있는 경우
      // response.getWriter().print("text") : 리퀘스트를 보낸 곳으로 객체를 전달한다.;
      else {
         //백엔드 측에 출력될 에러 메시지
         System.out.println("Token이 존재하지 않거나 잘못된 유형의 토큰입니다.");
         response.setStatus(HttpServletResponse.SC_NOT_FOUND);
         PrintWriter writer = response.getWriter();
         writer.println("HTTP Status 404 / ErrCode : 500 , Msg : Token이 존재하지 않거나 잘못된 유형의 토큰입니다.");
      }
   }
}