package com.skrookies.rentmarket.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Pointcut("execution(* com.skrookies.rentmarket.domain.*.controller..*(..)) || execution(* com.skrookies.rentmarket.domain.*.service..*(..)) || execution(* com.skrookies.rentmarket.domain.*.mapper..*(..))")
    private void loggerTarget() {

    }

    @Around("loggerTarget()")
    public Object loggerPointer(ProceedingJoinPoint joinPoint) throws Throwable {
        String type = "";
        String className = joinPoint.getSignature().getDeclaringTypeName();
        if (className.indexOf("Controller") > -1) {
            type = "[Controller]";
        } else if (className.indexOf("ServiceImpl") > -1) {
            type = "[ServiceImpl]";
        } else if (className.indexOf("Mapper") > -1) {
            type = "[Mapper]";
        }
        String methodName = joinPoint.getSignature().getName();

        log.debug(type + " " + className + "." + methodName);
        return joinPoint.proceed();
    }
}
