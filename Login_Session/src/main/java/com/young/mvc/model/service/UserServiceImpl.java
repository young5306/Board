package com.young.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.mvc.model.dao.UserDao;
import com.young.mvc.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// 1. 회원가입
	@Override
	public boolean signup(User user) {
		int result = userDao.insertUser(user);
		return result == 1;
	}
	
	// 2. 로그인 사용자 존재하는지 확인
	@Override
	public User checkLoginUser(User user) {
		// id로 사용자 불러와서 비밀번호가 입력된 비밀번호와 일치하는지 확인
		User loginUser = userDao.findUserByLoginId(user.getLoginId());
		if(loginUser == null || !user.getPassword().equals(loginUser.getPassword())) {
			return null;
		}
		return loginUser;
	}

	// 3. 회원탈퇴
	@Override
	public boolean deleteUser(String loginId, String password) {
		User loginUser = userDao.findUserByLoginId(loginId);
		if(loginUser == null || !password.equals(loginUser.getPassword())) return false;
		int result = userDao.deleteUser(loginUser);
		return result == 1;
	}

	// 4. 사용자 목록
	@Override
	public List<User> getUsers() {
		return userDao.selectAll();
	}
	

}
