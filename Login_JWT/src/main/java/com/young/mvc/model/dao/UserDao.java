package com.young.mvc.model.dao;

import com.young.mvc.model.dto.User;

public interface UserDao {
	
	// 1. 회원가입
	public int insertUser(User user);
	// 2. 로그인
	public User findUserByLoginId(String loginId);

}
