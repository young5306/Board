package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserDao {
	
	// 1. 로그인 (1명 조회)
	public User selectOne(String userId);
	
	// 2. 로그아웃 -> 필요x (컨트롤러에서 세션 만료시킬거임)
	// 3. 회원가입 (등록)
	public int insertUser(User user);
	
	// 4. 사용자 목록 전체 가져오기
	public List<User> selectAll();

}

/* map으로 로그인 가능
public User selectOne(Map<String, String> info);

*/
