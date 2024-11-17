package com.example.demo.model.service;

import com.example.demo.model.dto.User;

public interface UserService {
	
	public User login(String email, String password);

}
