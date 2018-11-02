package com.mybatis.demo.rest;

import javax.servlet.http.HttpServletRequest;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mybatis.demo.service.UserService;

public class ManageMultipleAnnotation {

	@Mock
	private HttpServletRequest mockHttpServletRequest;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserRestController userRestController;
	
	/**
	 * @return the mockHttpServletRequest
	 */
	public HttpServletRequest getMockHttpServletRequest() {
		return mockHttpServletRequest;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @return the userRestController
	 */
	public UserRestController getUserRestController() {
		return userRestController;
	}

	public ManageMultipleAnnotation() {

	}

}
