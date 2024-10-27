package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserService {
	
	// 1. 로그인 사용자 찾기
	public User login(String userId);
	// 2. 회원가입
	public void signup(User user);
	// 3. 사용자 목록
	public List<User> getUserList();

}
