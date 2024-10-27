package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserDao {
	
	// 1. 로그인
	public User selectOne(String userId);
	
	// 2. 회원가입
	public void insertUser(User user);
	
	// 3. 사용자 목록
	public List<User> selectAll();
}


/*map으로 로그인
public User selectOne(Map<String, String> info);
*/
