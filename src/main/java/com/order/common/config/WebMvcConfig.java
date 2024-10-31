package com.order.common.config; // 공통 설정 패키지 임포트

import com.users.common.interceptor.AuthorizationInterceptor;
import com.users.common.interceptor.LoggerInterceptor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Spring에 의해 설정 클래스로 인식되도록 마킹
public class WebMvcConfig implements WebMvcConfigurer { // WebMvcConfigurer 인터페이스를 구현하여 MVC 설정을 커스터마이징

    @Autowired // AuthorizationInterceptor를 의존성 주입
    private AuthorizationInterceptor authorizationInterceptor;

    // 공개 API 경로 목록
    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    // 기본 제외 경로 목록
    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    // Swagger 관련 경로 목록
    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v1/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LoggerInterceptor 등록
        registry.addInterceptor(new LoggerInterceptor());

        // AuthorizationInterceptor 등록
        registry.addInterceptor(authorizationInterceptor)
                // 공개 API 및 static 리소스에 대한 접근을 제외
                .excludePathPatterns("/public/**", "/open-api/**")
                .excludePathPatterns("/static/**", "/css/**", "/js/**")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS 설정
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정
                .allowedOrigins("http://localhost:3000") // 허용된 출처
                .allowedMethods("GET", "POST", "DELETE", "PUT") // 허용된 HTTP 메서드
                .allowedHeaders("*") // 허용할 헤더
                .allowCredentials(true); // 자격 증명 허용
    }
}