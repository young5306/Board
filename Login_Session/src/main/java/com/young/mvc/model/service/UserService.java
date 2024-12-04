package com.young.mvc.model.service;

import java.util.List;

import com.young.mvc.model.dto.User;

public interface UserService {
	// 1. 회원가입
	public boolean signup(User user);
	// 2. 로그인 사용자 존재하는지 확인
	public User checkLoginUser(User user);
	// 3. 회원탈퇴
	public boolean deleteUser(String loginId, String password);
	// 4. 사용자 목록
	public List<User> getUsers();
}
