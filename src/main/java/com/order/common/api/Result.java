package com.order.common.api; // 공통 API 응답 패키지 임포트

import com.users.common.error.ErrorCode;
import com.users.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 자동으로 getter/setter/toString/hashCode/equal 메서드 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
@Builder // 빌더 패턴 사용 가능하게 함
public class Result {

    private Integer resultCode; // 결과 코드 (예: 성공, 오류 코드)
    private String resultMessage; // 결과 메시지 (결과에 대한 간단한 설명)
    private String resultDescription; // 결과에 대한 상세 설명

    // 성공 결과를 생성하는 static 메서드
    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode()) // 성공 코드 설정
                .resultMessage(ErrorCode.OK.getDescription()) // 성공 메시지 설정
                .resultDescription("성공") // 결과 설명 설정
                .build(); // Result 객체 반환
    }

    // 오류 결과를 생성하는 static 메서드 (Throwable 객체 없음)
    public static Result ERROR(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode()) // 오류 코드 설정
                .resultMessage(errorCodeIfs.getDescription()) // 오류 메시지 설정
                .resultDescription("에러발생") // 결과 설명 설정
                .build(); // Result 객체 반환
    }

    // 오류 결과를 생성하는 static 메서드 (Throwable 객체 포함)
    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode()) // 오류 코드 설정
                .resultMessage(errorCodeIfs.getDescription()) // 오류 메시지 설정
                .resultDescription(tx.getLocalizedMessage()) // 예외의 로컬 메시지를 결과 설명에 설정
                .build(); // Result 객체 반환
    }

    // 오류 결과를 생성하는 static 메서드 (커스텀 설명 포함)
    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode()) // 오류 코드 설정
                .resultMessage(errorCodeIfs.getDescription()) // 오류 메시지 설정
                .resultDescription(description) // 커스텀 설명 설정
                .build(); // Result 객체 반환
    }
}