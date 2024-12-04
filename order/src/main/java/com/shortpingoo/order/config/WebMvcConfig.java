package com.shortpingoo.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://*.elb.amazonaws.com", "http://localhost:3000", "http://175.120.35.228:3000", "https://shortpingoo.shop")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}