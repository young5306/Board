package com.young.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.young.mvc.model.dto.User;
import com.young.mvc.model.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user-api")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/** 회원가입
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user){
		System.out.println("======signup======");
		System.out.println("");
		boolean isInserted = userService.signup(user);
		if(!isInserted) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("가입 실패");	
		}
		return ResponseEntity.status(HttpStatus.OK).body("가입 완료");
	}
	// @RequestParam : 폼 데이터를 url의 쿼리 파라미터로 하나씩 받음 (객체X)
	// @ModelAttribute : 폼 데이터를 객체에 바인딩 가능
	// @RequestBody : json 데이터(요청 본문)를 객체로 받음
	
	/** 로그인
	 * 세션에 loginId만 저장 (비번 포함하는 user객체 저장 시 보안 문제)
	 * 외부적으로는/User 가져올 때는 loginId 사용.
	 * userId는 내부적으로/외래키로 사용되어 게시글이나 댓글 가져올 떄 사용 
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
		User loginUser = userService.checkLoginUser(user);
		 System.out.println("======login======");
		 System.out.println("loginUser찾기 : "+ loginUser);
		if(loginUser==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자가 존재하지 않습니다.");
		session.setAttribute("loginId", loginUser.getLoginId());
		session.setMaxInactiveInterval(1800); // 1800초 (30분)
		 System.out.println("로그인 시 생성된 세션 : " +session);
		 System.out.println("로그인 시 생성된 세션 객체 : " + session.getAttribute("loginId"));
		Map<String, Object> res = new HashMap<>();
		res.put("loginId", session.getAttribute("loginId"));
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	/** 3. 로그아웃
	 * 보안 PostMapping
	 * 서버 측 세션 무효화 (쿠키에 남아있는 jsessionid 안지워도 됨. 새로운 요청 오면 새로운 세션 생성될것)
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response){
		System.out.println("======logout======");
		System.out.println("invalidate전 세션 : " +session.getAttribute("loginId"));
		session.invalidate();
		System.out.println("invalidate후 세션 : " +session);
		
		// 클라이언트 쿠키 삭제 - 보안 조금 더 강화, 사용자 경험 향상
//	    Cookie cookie = new Cookie("JSESSIONID", null);
//	    cookie.setPath("/"); // 애플리케이션의 루트 경로에 대한 쿠키
//	    cookie.setHttpOnly(true);
//	    cookie.setMaxAge(0); // 쿠키를 즉시 만료
//	    response.addCookie(cookie);
	    
		return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
	}
	// postman에서 cookie에는 아직 jsessionid가 담겨 있음. (세션만 무효화한거라)
	
	/** 4. 회원탈퇴
	 * 비번 입력받아서 일치하면 회원탈퇴 가능하게 만듦 
	 * 비번은 보안을 위해 @RequestParam이 아닌 @RequestBody로 받음
	 * 비번 검증 로직은 비즈니스 로직이기 때문에 서비스단에서 확인하는 것이 적합, 유지보수에 좋음
	 * 
	 * @param password
	 * @param session
	 * @return
	 */
	@DeleteMapping("/signout")
	public ResponseEntity<String> signout(@RequestBody User user, HttpSession session){
		System.out.println("======signout======");
		String loginId = (String) session.getAttribute("loginId");
		String password = user.getPassword();
		if(loginId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("로그인 정보가 없습니다.");
		
	    boolean isDeleted = userService.deleteUser(loginId, password);
	    if (!isDeleted) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 실패");
	    }
	    
	    session.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body("회원탈퇴 성공");
	}
	
	/** 5. 세션 확인 (userId 반환)
	 * 프론트단에서 로그인 시 loginId에 로그인 사용자 식별하기 위해
	 * 현재 세션에 들어있는 loginId 반환 
	 * 
	 * @param session
	 * @return
	 */
//	@GetMapping("/current")
//	public ResponseEntity<Map<String, Object>> getCurrentUser(HttpSession session){
//		String loginId = (String) session.getAttribute("loginId");
//		  System.out.println("======current======");
//		  System.out.println("현재 세션 : " +session);
//		  System.out.println("현재 세션 loginId : "+loginId);
//		if(loginId != null) {
//			Map<String, Object> res = new HashMap<>();
//			res.put("loginId", loginId);
//			return ResponseEntity.status(HttpStatus.OK).body(res);
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
	/** 6. 사용자 목록
	 * 
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<User>> getUsers(HttpSession session){
		List<User> users = userService.getUsers();
		System.out.println("======사용자목록======");
		System.out.println("세션 : "+session);
		System.out.println("users : "+users);
		if(users == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	
	
}
