package com.young.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.young.mvc.model.dao")
public class LoginSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSessionApplication.class, args);
	}

}

