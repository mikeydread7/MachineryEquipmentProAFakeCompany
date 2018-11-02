package com.mybatis.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mybatis.demo.generics.dao.GenericMapperImpl;
import com.mybatis.demo.mapper.UserMapper;
import com.mybatis.demo.model.User;

@Repository
public class UserRepositoryImpl extends GenericMapperImpl<User, Integer>implements UserRepository {

	@Autowired
	private UserMapper userMapper;

	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.repository.UserRepository#selectUserById(int)
	 */
	@Override
	public User selectUserById(Integer userId) {
		User user = (User) userMapper.findOne(userId);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.repository.UserRepository#saveOrUpdateUser(com.mybatis.
	 * demo.model.User, int)
	 */
	@Override
	public void saveOrUpdateUser(User user, Integer id) {

		userMapper.save(user, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.repository.UserRepository#insertUser(com.mybatis.demo.
	 * model.User)
	 */
	@Override
	public void insertUser(User user) {
		userMapper.insert(user);
	}
	
	@Override
	public void insert (User user, Object map){
		 throw new RuntimeException("not supported");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.repository.UserRepository#deleteUser(int)
	 */
	@Override
	public void deleteUser(Integer userId) {
		userMapper.delete(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.repository.UserRepository#selectUserAllUsers()
	 */
	@Override
	public List<User> selectUserAllUsers() {
		return userMapper.findAll();

	}
		

}
