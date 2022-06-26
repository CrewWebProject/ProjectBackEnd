package com.project.crewwebproject.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
   //유저가 서버에 요청을 할 때 액세스가 가능한지 권한을 체크후 액세스 할 수 없는 요청을 했을시 동작된다.
   //요청을 보낸 유저의 권한을 확인 후 권한이 없는 유저일 경우 예외를 처리하는 AccessDeniedHandler를 구현한다.
   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      //필요한 권한 없이 접근하려 할때 403
      System.out.println("해당 메서드에 접근하기 위한 권한이 부족합니다. 본인의 권한을 다시 확인해주세요.");
      //백엔드 측에 출력될 에러 메시지
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      // response.getWriter().print("text") : 리퀘스트를 보낸 곳으로 객체를 전달한다.;
      PrintWriter writer = response.getWriter();
      writer.println("HTTP Status 403 / ErrCode : 999 , Msg : 해당 API의 호출 권한이 없는 유저입니다.");
   }
}
