package com.rituals.basket.pooja.market.data.service.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOriginPatterns("*") // Allow all domains (including our new S3 website)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Allow all REST actions
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(false); // Can't be true if allowedOriginPatterns is *
    }
}
