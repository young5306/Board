package com.young.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.young.mvc.model.dto.User;
import com.young.mvc.model.service.UserService;
import com.young.mvc.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-api2")
@RequiredArgsConstructor
// == @Autowired 대신 사용 == 
// 초기화되지 않은 필드나, @NonNull이 붙은 필드에 대해 생성자 생성
// 롬복으로 스프링에서 DI(의존성 주입) 중 생성자 주입으로 자동으로 해줌 
public class UserController {
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	// 1. 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		if(userService.signup(user)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
	}
	
	// 2. 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(user); 
		
		if(loginUser != null) {
			result.put("message", "로그인 성공");
			result.put("loginId", loginUser.getLoginId());
			result.put("access-token", jwtUtil.createToken(loginUser.getLoginId()));
			System.out.println("");
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.notFound().build();
	}
	
	// 3. 로그아웃
	
	
	// 4. 회원탈퇴

}
