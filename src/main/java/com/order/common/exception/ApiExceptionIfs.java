package com.order.common.exception; // 예외 패키지 임포트

import com.users.common.error.ErrorCodeIfs;

// API 예외를 위한 인터페이스
public interface ApiExceptionIfs {

    // 오류 코드 인터페이스를 반환하는 메서드
    ErrorCodeIfs getErrorCodeIfs();

    // 오류 설명을 반환하는 메서드
    String getErrorDescription();
}
