//package com.young.mvc.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("http://localhost:5173"); // 프론트엔드 주소 허용
//        corsConfig.addAllowedMethod("GET"); // GET 메서드 허용
//        corsConfig.addAllowedMethod("POST"); // POST 메서드 허용
//        corsConfig.addAllowedMethod("PUT"); // PUT 메서드 허용
//        corsConfig.addAllowedMethod("DELETE"); // DELETE 메서드 허용
//        corsConfig.addAllowedMethod("OPTIONS"); // OPTIONS 메서드 허용
//        corsConfig.addAllowedHeader("Content-Type"); // 모든 헤더 허용
//        corsConfig.setAllowCredentials(true); // 쿠키 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig); // 모든 경로에 대해 CORS 설정 적용
//
//        return new CorsFilter(source);
//    }
//}
