package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.UserDao;
import com.ssafy.mvc.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String userId) {
		return userDao.selectOne(userId);
	}

	@Override
	public void signup(User user) {
		userDao.insertUser(user);
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

}

/* map으로 로그인
@Override
public User login(String id, String password) {
	Map<String, String> info = new HashMap<>();
	info.put("id", id);
	info.put("password", password);
	User tmp = userDao.selectOne(info);
	return tmp;
}
*/