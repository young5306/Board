package com.young.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.young.mvc.filter.TokenAuthFilter;
import com.young.mvc.util.JwtUtil;

import jakarta.servlet.Filter;

@Configuration // Spring에서 설정 클래스를 나타내는 어노테이션
// Spring Security는 아직 사용 X - 커스텀 필터 등록
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
	@Autowired
	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	public FilterRegistrationBean<TokenAuthFilter> tokenAuthFilter(){
		FilterRegistrationBean<TokenAuthFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new TokenAuthFilter(jwtUtil));
		bean.setOrder(1); // 순서 : 낮을수록 더 먼저 동작
		bean.addUrlPatterns("/user-api2/"); // 필터를 적용할 URL 패턴 설정
		return bean;
	}
}
