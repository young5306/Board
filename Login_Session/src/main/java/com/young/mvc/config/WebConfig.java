package com.young.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.young.mvc.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LoginInterceptor loginInterceptor;

	@Autowired
	public WebConfig(LoginInterceptor loginInterceptor) {
		this.loginInterceptor = loginInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 요청에 대해 CORS 허용
				.allowedOrigins("http://localhost:5173") // 허용되는 요청 헤더의 Origin을 설정 (프론트엔드 주소)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 요청 시 허용되는 http method를 설정
				.allowedHeaders("*") // 허용할 헤더
				.allowCredentials(true);
	}

	// LoginInterceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**") // 모든 경로에 대해 인터셉터 적용
				.excludePathPatterns("/user-api/login", "/user-api/signup")
				.excludePathPatterns("OPTIONS"); // OPTIONS
																											// 요청도 제외
	}

}
