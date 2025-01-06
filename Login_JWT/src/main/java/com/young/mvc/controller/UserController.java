package com.young.mvc.controller;

import java.net.http.HttpRequest;
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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController
@RequestMapping("/user-api2")
@RequiredArgsConstructor
// == @Autowired 대신 사용 == 
// 초기화되지 않은 필드나, @NonNull이 붙은 필드에 대해 생성자 생성
// 롬복으로 스프링에서 DI(의존성 주입) 중 생성자 주입으로 자동으로 해줌 
public class UserController {
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	/** 1. 회원가입
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		if(userService.signup(user)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
	}
	
	/** 2. 로그인
	 * 응답 본문에 result = { message:"", loginId:""} 담아 보냄
	 * 토큰은 헤더로 주고 받음.
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(user); 
		
		if(loginUser != null) {
			result.put("message", "로그인 성공");
//			result.put("loginId", loginUser.getLoginId());
			
			String jwtToken = jwtUtil.createToken(loginUser.getLoginId());
			log.info("login access-token: {}", jwtToken);
			return ResponseEntity.status(HttpStatus.OK).header("Authorization", "Bearer "+jwtToken).body(result);
		}
		result.put("message", "사용자 없음");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}
	
	// 3. 로그아웃
	// 유효성 검사는 filter에서 진행.
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request){
		// JWT는 서버에서 관리되지 않으므로 서버에서 별도로 할 작업은 없음
        // 클라이언트에서 JWT를 삭제하도록 유도
		log.info("logout access-token: {}", request.getHeader("Authorization"));
        return ResponseEntity.ok("로그아웃 성공");
	}
	
	
	
	// 4. 회원탈퇴

}
