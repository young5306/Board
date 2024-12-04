package com.young.mvc.model.service;

import org.springframework.stereotype.Service;

import com.young.mvc.model.dao.UserDao;
import com.young.mvc.model.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	// 1. 회원가입
	@Override
	public boolean signup(User user) {
		int result = userDao.insertUser(user);
		return result == 1;
	}

	// 2. 로그인
	@Override
	public User login(User user) {
		User loginUser = userDao.findUserByLoginId(user.getLoginId());
		if(loginUser == null || !user.getPassword().equals(loginUser.getPassword())) {
			return null;
		}
		return loginUser;
	}

}
