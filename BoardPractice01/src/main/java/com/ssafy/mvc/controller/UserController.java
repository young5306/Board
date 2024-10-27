package com.ssafy.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.mvc.model.dto.User;
import com.ssafy.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("login")
	public String login() {
		return "/user/loginform";
	}

	// 로그인
	@PostMapping("login")
	public String login(@ModelAttribute User user, HttpSession session) {
		User loginUser = userService.login(user.getUserId());
		if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("loginUser", user.getUserId());
		} else {
			return "redirect:login"; // GET
		}
		return "redirect:list";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:list";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "/user/signupform";
	}
	
	// 회원가입
	@PostMapping("signup")
	public String signup(@ModelAttribute User user) {
		userService.signup(user);
		return "redirect:list";
	}
	
	@GetMapping("users")
	public String userList(Model model) {
		model.addAttribute("users", userService.getUserList());
		return "/user/adminPage";
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