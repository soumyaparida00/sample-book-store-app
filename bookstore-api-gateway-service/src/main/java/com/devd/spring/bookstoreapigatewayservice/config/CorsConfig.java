package com.devd.spring.bookstoreapigatewayservice.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Global CORS configuration for the API gateway so browsers can call the
 * backend from permitted origins (e.g., CloudFront/custom domain).
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Allow common methods used by the frontend, including preflight.
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        // Allow typical headers including Authorization for OAuth/token calls.
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "Origin", "Accept"));
        // Add the origins you want to permit. Update this list for your domains.
        config.setAllowedOrigins(Arrays.asList(
                "https://store.virtualtechbox.com",
                "https://d10xukofez1olh.cloudfront.net",
                "http://localhost:3000",
                "http://localhost:8765",
                "http://10.8.10.19:8765"
        ));
        config.setAllowCredentials(false);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
