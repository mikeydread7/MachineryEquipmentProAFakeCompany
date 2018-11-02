package com.mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.generics.service.GenericServiceImpl;
import com.mybatis.demo.model.User;
import com.mybatis.demo.repository.UserRepository;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(@Qualifier("userRepositoryImpl")GenericMapper<User, Integer> userRepository) {
		super(userRepository);
		this.userRepository =(UserRepository) userRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#selectUserById(int)
	 */
	@Override
	public User selectUserById(Integer userId) {

		return userRepository.selectUserById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#saveUser(com.mybatis.demo.model.
	 * User)
	 */
	@Override
	public void saveUser(User user) {
		userRepository.insertUser(user);

	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#deleteUser(int)
	 */
	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#updateUser(com.mybatis.demo.model.
	 * User, int)
	 */
	@Override
	public void updateUser(User user, Integer id) {
		userRepository.saveOrUpdateUser(user, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#selectAllUsers()
	 */
	@Override
	public List<User> selectAllUsers() {

		return userRepository.selectUserAllUsers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#exists(com.mybatis.demo.model.User)
	 */
	@Override
	public Boolean exists(User user) {
		return (null == userRepository.selectUserById(user.getUserId()));

	}
}
