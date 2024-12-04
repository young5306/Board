package com.young.mvc.model.dao;

import java.util.List;

import com.young.mvc.model.dto.User;

public interface UserDao {
	// 1. 회원가입
	public int insertUser(User user);
	// 2. 로그인 사용자 존재하는지 확인
	public User findUserByLoginId(String loginId);
	// 3. 회원탈퇴
	public int deleteUser(User user);
	// 4. 사용자목록
	public List<User> selectAll();
}
