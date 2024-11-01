package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.dto.User;
import com.ssafy.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserRestController {
	
	private final UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 1. 로그인
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session){
		User loginUser = userService.login(user.getUserId());
		System.out.println(loginUser);
		if(loginUser != null && loginUser.getPassword().equals(user.getPassword())){
			session.setAttribute("loginUser", user.getUserId());
			return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 실패");
	}
	
	// 2. 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok().body("로그아웃 완료");
	}

	// 3. 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		boolean isSignedUp = userService.signup(user);
		if(isSignedUp) {
			return ResponseEntity.ok().body("회원가입 완료");
		}
		return ResponseEntity.badRequest().body("회원가입 실패");
	}
	
	// 4. 사용자 목록 전체 가져오기
	@GetMapping("/users")
	public ResponseEntity<?> list(){
		List<User> list = userService.getUserList();
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	

}

/* map으로 로그인

@PostMapping("/login")
public String login(@ModelAttribute User user, HttpSession session) {
	User tmp = userService.login(user.getId(), user.getPassword());
	//tmp : 정상로그인 -> User정보 / 비정상로그인 -> null
	if(tmp == null) {
		return "redirect:login"; //로그인화면으로 보내잉!
	}
	//아래의 코드가 실행된다는 것은! (로그인이 제대로 OK라는 뜻)
	session.setAttribute("loginUser", tmp.getName());
	return "redirect:list";
}
*/