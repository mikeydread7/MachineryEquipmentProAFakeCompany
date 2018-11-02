package com.mybatis.demo.service;

import java.util.List;

import com.mybatis.demo.generics.service.GenericService;
import com.mybatis.demo.model.User;

public interface UserService extends GenericService<User,  Integer> {

	User selectUserById(Integer userId);

	void saveUser(User user);
	
	void deleteUser(Integer userId);

	void updateUser(User user, Integer id);

	List<User> selectAllUsers();

	Boolean exists(User user);

}