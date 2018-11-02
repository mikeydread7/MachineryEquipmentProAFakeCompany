package com.mybatis.demo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.User;
import com.mybatis.demo.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

	@Mock
	private HttpServletRequest mockHttpServletRequest;
	@Mock
	private UserService userService;

	@InjectMocks
	private UserRestController userRestController;

	@Before
	public void setup() {
		HttpServletRequest mockRequest = new MockHttpServletRequest();
		ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
		RequestContextHolder.setRequestAttributes(servletRequestAttributes);
	}

	@After
	public void teardown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllUsers() {
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/user");
		when((userService.selectAllUsers())).thenReturn(MockTestList.mockEntityUserList);
		ResponseEntity<?> results = userRestController.getAllUser();
		assertTrue("Found values", ((List<User>) results.getBody()).size() == 4);
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}

	@Test
	public void testFindUser() {

		Integer userId = 3;
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/user/id/".concat(Integer.toString(userId)));
		when((userService.selectUserById(userId))).thenReturn(find(userId));
		ResponseEntity<?> results = userRestController.getUser(userId);
		assertTrue("Found values", ((User) results.getBody()).getUserAge().equals(5));
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}

	@Test
	public void testFindUserAgain() {

		Integer userId = 4;
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/user/id/".concat(Integer.toString(userId)));
		when((userService.selectUserById(userId))).thenReturn(find(userId));
		ResponseEntity<?> results = userRestController.getUser(userId);
		assertTrue("Found values", ((User) results.getBody()).getUserAge().equals(6));
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}

	// *************************************************************************************************
	// Utility
	private User find(Integer userId) {

		return MockTestList.mockEntityUserList.stream()
				.filter(u->u.getUserId()== userId)
				.findFirst()
				.orElse(new User());
	}
}
