package com.mybatis.demo.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.User;

public class UserRepositoryImplTest  {

	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindTAllUser() {
		when(userRepository.findAll()).thenReturn(MockTestList.mockEntityUserList);
		assertTrue(userRepository.findAll().size() > 0);
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void testFindOneUserById() {
		when(userRepository.findOne(2)).thenReturn(
				MockTestList.mockEntityUserList.stream().filter(e -> e.getUserId() == 2).findFirst().orElse(null));
		User user = userRepository.findOne(2);
		assertTrue(user != null && user.getEyeColor() != null);
		verify(userRepository, times(1)).findOne(2);
	}

	@Test
	public void testInsertUser() {

		doNothing().when(userRepository).insert(MockTestList.mockEntityUserList.get(1));
		userRepository.insert(MockTestList.mockEntityUserList.get(1));
		verify(userRepository, times(1)).insert(MockTestList.mockEntityUserList.get(1));
		verify(userRepository, never()).delete(1);
	}

	@Test
	public void testSaveOrUpdate() {
		User user = MockTestList.mockEntityUserList.get(1);
		doNothing().when(userRepository).save(user, user.getUserId());
		verify(userRepository, never()).delete(user.getUserId());
		verify(userRepository, never()).findOne(user.getUserId());
	}

}
