package com.order.common.aop; // AOP(Aspect Oriented Programming) 관련 패키지 임포트

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j // 로깅을 위한 SLF4J 로거 생성
@Aspect // AOP Aspect를 정의하는 어노테이션
@Component // Spring의 컴포넌트로 등록
public class LoggerAspect {

    // 로그를 찍을 메서드의 타겟을 정의하는 포인트컷
    @Pointcut("execution(* com.rentmarket.backend.domain.*.controller..*(..)) || execution(* com.rentmarket.backend.domain.*.service..*(..)) || execution(* com.rentmarket.backend.domain.*.mapper..*(..))")
    private void loggerTarget() {
        // 포인트컷의 본체는 비워둡니다. 이 메서드는 호출되지 않지만,
        // 포인트컷 표현식에서 참조됩니다.
    }

    // 지정된 포인트컷에 해당하는 메서드 호출을 감싸고, 로그를 기록하는 어드바이스
    @Around("loggerTarget()")
    public Object loggerPointer(ProceedingJoinPoint joinPoint) throws Throwable {
        String type = ""; // 로그 유형을 저장할 변수
        String className = joinPoint.getSignature().getDeclaringTypeName(); // 호출된 메서드의 클래스 이름

        // 클래스 이름에 따라 로그 유형을 결정
        if (className.indexOf("Controller") > -1) {
            type = "[Controller]"; // 컨트롤러일 경우
        } else if (className.indexOf("ServiceImpl") > -1) {
            type = "[ServiceImpl]"; // 서비스 구현 클래스일 경우
        } else if (className.indexOf("Mapper") > -1) {
            type = "[Mapper]"; // 매퍼 클래스일 경우
        }

        String methodName = joinPoint.getSignature().getName(); // 호출된 메서드 이름

        // 로그 출력: 로그 유형, 클래스 이름 및 메서드 이름
        log.debug(type + " " + className + "." + methodName);

        return joinPoint.proceed(); // 원래 메서드를 실행하고 그 결과를 반환
    }
}