package com.ssafy.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.mvc.interceptor.LoginInterceptor;

@SpringBootApplication // 내부에 @Configuration 존재
@MapperScan(basePackages = "com.ssafy.mvc.model.dao")
public class BoardRestPracticeApplication implements WebMvcConfigurer {

	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/api-board/**");
		//.excludePathPatterns("/", "/api-user/login", "/api-user/signup", "/api-board/board");
		// get만 처리?
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BoardRestPracticeApplication.class, args);
	}

}


/* DBConfig
@Configuration // 설정파일임을 알림.
@MapperScan(basePackages = "com.ssafy.mvc.model.dao")
public class DBConfig {
}
=> dao 경로의 클래스들을 빈으로 등록, mappers/*.xml파일과 연결
*/

/* webconfig
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Autowired
	AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/*").excludePathPatterns("/", "/index.html", "/login", "/list", "/signup");
	}	
}
*/
