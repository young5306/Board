package com.ssafy.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // @Configuration 포함
@MapperScan(basePackages = "com.ssafy.mvc.model.dao") 
public class PairDemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PairDemoApplication.class, args);
	}

}
