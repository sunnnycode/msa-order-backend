package com.order.common.config; // 공통 설정 패키지 임포트
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
@Configuration // Spring에 의해 설정 클래스로 인식되도록 마킹
public class SecurityConfig {
    @Bean // Bean으로 등록하여 Spring 컨텍스트에서 관리되도록 함
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> { // CORS 설정
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000")); // 명시적으로 React 개발 서버의 출처를 설정
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드 설정
                    config.setAllowedHeaders(List.of("*")); // 모든 헤더를 허용
                    config.setAllowCredentials(true); // 자격 증명 허용
                    return config; // CORS 설정 반환
                }))
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(authz -> authz // HTTP 요청에 대한 권한 부여 설정
                        .requestMatchers("/open-api/gpt/**").permitAll() // GPT API에 대한 접근을 허용
                        .anyRequest().permitAll() // 모든 다른 요청도 허용
                );

        return http.build(); // 설정된 보안 필터 체인을 반환
    }

    @Bean // Bean으로 등록하여 Spring 컨텍스트에서 관리되도록 함
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 인스턴스 반환
    }
}