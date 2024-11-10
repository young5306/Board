package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserDao {
	// 사용자 목록
	public List<User> selectAll();
}
