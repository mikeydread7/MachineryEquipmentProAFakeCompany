package com.mybatis.demo.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.MachineEquipmentPro;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.User;
import com.mybatis.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MachineEquipmentPro.class)
public class UserServiceIntegrationTest implements MockTestList {

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl(userRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAll() {
		when(userRepository.selectUserAllUsers()).thenReturn(mockEntityUserList);
		assertTrue(userService.selectAllUsers().size() > 1);
	}

	@Test
	public void testById() {
		int userId = 2;
		when(userRepository.selectUserById(userId)).thenReturn(
				mockEntityUserList.stream().filter(e -> e.getUserId().intValue() == userId).findFirst().orElse(null));
		assertTrue(userService.selectUserById(userId).getEyeColor().equals("brown"));
	}

	@Test
	public void testSaveOrUpdate() {

		User user = new User(1, "FOO", "brown", 3, 120, new Date(100L), 3);
		userRepository.saveOrUpdateUser(user, 3);
		verify(userRepository, never()).deleteUser(1);

	}

	@Test
	public void testDelete() {

		userRepository.deleteUser(1);
		verify(userRepository, never()).deleteUser(4);
		verify(userRepository, never()).selectUserById(4);
	}

	@Test
	public void testFindById() {
		int userId = 3;
		when(userRepository.selectUserById(userId)).thenReturn(
				mockEntityUserList.stream().filter(e -> e.getUserId().intValue() == userId).findFirst().orElse(null));
		assertTrue(userService.selectUserById(userId).getUserName().equals("FOOBAR"));

	}

}