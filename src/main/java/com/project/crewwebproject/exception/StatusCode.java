package com.project.crewwebproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {
    OK(HttpStatus.OK, "0", "응답이 정상 처리 되었습니다."),

    LOGIN_USER_ID_FAIL(HttpStatus.NOT_FOUND, "110", "해당 하는 memberId 가 없습니다"),

    LOGIN_PASSWORD_FAIL(HttpStatus.BAD_REQUEST, "111", "잘못된 Password 입니다."),

    LOGIN_WRONG_SIGNATURE_JWT_TOKEN(HttpStatus.BAD_REQUEST, "112", "잘못된 형식의 JWT 서명입니다."),

    LOGIN_EXPIRED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "113", "만료된 JWT 토큰 기간이 만료되었습니다."),

    LOGIN_NOT_SUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "114", "지원하지 않는 JWT 토큰입니다."),

    LOGIN_WRONG_FORM_JWT_TOKEN(HttpStatus.BAD_REQUEST, "115", "JWT 토큰 내용이 형식과 다릅니다."),


    INTERNAL_SERVER_ERROR_PLZ_CHECK(HttpStatus.INTERNAL_SERVER_ERROR, "999", "알수없는 서버 내부 에러 발생 , dladlsgur3334@gmail.com 으로 연락 부탁드립니다.");

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String statusMsg;

    StatusCode(HttpStatus httpStatus, String statusCode, String statusMsg) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
