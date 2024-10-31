package com.order.common.api; // 공통 API 응답 패키지 임포트

import com.users.common.error.ErrorCodeIfs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 자동으로 getter/setter/toString/hashCode/equal 메서드 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class Api<T> {

    private Result result; // API 결과를 담는 필드
    @Valid // body에 대한 유효성 검사
    private T body; // 실제 API 데이터 본문
    private String message; // 추가 메시지 (예: 오류 설명 등)
    private int statusCode; // HTTP 상태 코드

    // 성공적인 API 응답을 생성하는 static 메서드
    public static <T> Api<T> OK(T data){
        var api = new Api<T>(); // Api 객체 생성
        api.result = Result.OK(); // 결과를 성공으로 설정
        api.body = data; // body에 데이터 설정
        return api; // Api 객체 반환
    }

    // 오류 응답을 생성하는 static 메서드 (Result 객체 사용)
    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>(); // Api 객체 생성
        api.result = result; // 주어진 Result 객체로 설정
        return api; // Api 객체 반환
    }

    // 오류 응답을 생성하는 static 메서드 (ErrorCodeIfs 객체 사용)
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api<Object>(); // Api 객체 생성
        api.result = Result.ERROR(errorCodeIfs); // Result에서 오류 생성하여 설정
        return api; // Api 객체 반환
    }

    // 오류 응답을 생성하는 static 메서드 (ErrorCodeIfs와 Throwable 사용)
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        var api = new Api<Object>(); // Api 객체 생성
        api.result = Result.ERROR(errorCodeIfs, tx); // Result에서 오류 생성하여 설정
        return api; // Api 객체 반환
    }

    // 오류 응답을 생성하는 static 메서드 (ErrorCodeIfs와 커스텀 설명 사용)
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){
        var api = new Api<Object>(); // Api 객체 생성
        api.result = Result.ERROR(errorCodeIfs, description); // Result에서 오류 생성하여 설정
        return api; // Api 객체 반환
    }
}