package com.ssafy.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.mvc.interceptor.AdminInterceptor;
import com.ssafy.mvc.interceptor.LoginInterceptor;

@SpringBootApplication // @Configuration 포함
@MapperScan(basePackages = "com.ssafy.mvc.model.dao") // dao 인식할 수 있게 등록/스캔 (매퍼xml 대상 위치 정의, 매핑) 
public class BoardPractice01Application implements WebMvcConfigurer {
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Autowired
	AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/", "/index.html", "/login", "/list", "/signup");
		// index.html에 interceptor 적용 안되게 하려면 "/", "/index.html" 모두 필요
		registry.addInterceptor(adminInterceptor).addPathPatterns("/users");
		
		// addI등록, addP 
		// * : 모든 문자열 대체
		// ** : 하위 디렉토리 전부
		// ? : 문자 하나 대체
	}

	public static void main(String[] args) {
		SpringApplication.run(BoardPractice01Application.class, args);
	}

}

/* dbconfig
@Configuration
@MapperScan(basePackages = "com.ssafy.ws.model.dao")
public class DBConfig {
	
}
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