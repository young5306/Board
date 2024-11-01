package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserService {
	
	// 1. 로그인 (1명 조회)
	public User login(String userId);
	
	// 2. 로그아웃 -> 필요x (컨트롤러에서 세션 만료시킬거임)
	// 3. 회원가입 (등록)
	public boolean signup(User user);
	
	// 4. 사용자 목록 전체 가져오기
	public List<User> getUserList();
}
