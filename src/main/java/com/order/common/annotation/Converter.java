package com.order.common.annotation; // 사용자 정의 어노테이션 패키지

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

// 어노테이션이 클래스 레벨에서 사용됨을 나타냄
@Target(ElementType.TYPE)
// 런타임 시에도 어노테이션 정보를 유지함을 나타냄
@Retention(RetentionPolicy.RUNTIME)
// Spring의 서비스 컴포넌트로 등록하기 위한 어노테이션
@Service
public @interface Converter {

    // Service 어노테이션의 value 속성을 별칭으로 사용할 수 있도록 정의
    @AliasFor(annotation = Service.class)
    String value() default "";
}