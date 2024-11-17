package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.User;
import com.example.demo.model.service.UserService;
import com.example.demo.util.JwtUtil;


@RestController
@RequestMapping("api-user")
public class UserRestController {
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public UserRestController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		HttpStatus status = null;
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(user.getEmail(), user.getPassword());

		System.out.println(loginUser);
		if (loginUser != null) {
			result.put("message", "login 성공");
			result.put("access-token", jwtUtil.createToken(loginUser.getUserId(), loginUser.getUsername()));
			// id도 같이 넘겨주면 번거로운 작업을 할 필요는 없어
			status = HttpStatus.ACCEPTED;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(result, status);
	}

	// 회원가입

	// 로그아웃

	// 회원탈퇴

	// 소셜 로그인 따로?

	// 사용자 목록 가져오기

	
	
}
