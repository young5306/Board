package com.example.demo.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.dao.UserDao;
import com.example.demo.model.dto.User;


@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String email, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("email", email);
		info.put("password", password);
		User tmp = userDao.selectOne(info);
		return tmp;
	}

}
