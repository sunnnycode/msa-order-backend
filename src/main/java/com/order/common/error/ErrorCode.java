package com.order.common.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs {

    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
    NULL_POINT(INTERNAL_SERVER_ERROR.value(), 512, "Null point"),

    // NOT_FOUND 오류 코드 추가
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), 404, "존재하지 않는 사용자입니다."),

    // UNAUTHORIZED 오류 코드 추가
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), 401, "인증 실패");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
