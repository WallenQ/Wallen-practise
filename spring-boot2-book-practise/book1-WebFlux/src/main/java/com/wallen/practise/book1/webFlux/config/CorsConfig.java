package com.wallen.practise.book1.webFlux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @Author qianwenlong
 * @Date 2025/7/21 16:04
 */
@Configuration
public class CorsConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/echo") // 或者使用通配符 "/**"
                .allowedOrigins("http://localhost:8080") // 允许的来源
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
