package com.example.demo.model.dao;

import java.util.Map;

import com.example.demo.model.dto.User;



public interface UserDao {

	public User selectOne(Map<String, String> info);

}
