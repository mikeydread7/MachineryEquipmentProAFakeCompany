package com.mybatis.demo.repository;

import java.util.List;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.model.User;


public interface  UserRepository extends GenericMapper<User, Integer> {

	User selectUserById(Integer id);

	void saveOrUpdateUser(User user, Integer userrId);

	void insertUser(User  user);

	void deleteUser(Integer id);

	void insert (User user, Object map);
	
	List<User> selectUserAllUsers();

}