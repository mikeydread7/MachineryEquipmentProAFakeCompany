package com.mybatis.demo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybatis.demo.constants.MockTestList;

public class UserServiceTest {
	

	@Mock
	private  UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
    public void testMockCreation(){
        assertNotNull(userService);
    }

	@Test
	public void testAll() {
		when(userService.findAll()).thenReturn(MockTestList.mockEntityUserList);
		assertTrue(userService.findAll().size() > 1);
		verify(userService, times(1)).findAll();
	}

	@Test
	public void testById() {
		Integer userId = 2;
		when(userService.findOne(userId)).thenReturn(MockTestList.mockEntityUserList.stream()
				.filter(e -> e.getUserId().intValue() == userId).findFirst().orElse(null));
		assertTrue(userService.findOne(userId).getEyeColor().equals("brown"));
		verify(userService, times(1)).findOne(userId);
	}

	@Test
	public void testFindById() {
		Integer userId = 3;
		when(userService.findOne(userId)).thenReturn(MockTestList.mockEntityUserList.stream()
				.filter(e -> e.getUserId().intValue() == userId).findFirst().orElse(null));
		assertTrue(userService.findOne(userId).getUserId().equals(userId));

	}
}
