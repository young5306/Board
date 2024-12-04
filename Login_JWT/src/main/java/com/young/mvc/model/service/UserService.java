package com.young.mvc.model.service;

import com.young.mvc.model.dto.User;

public interface UserService {
	
	// 1. 회원가입
	public boolean signup(User user);
	// 2. 로그인
	public User login(User user);
}
