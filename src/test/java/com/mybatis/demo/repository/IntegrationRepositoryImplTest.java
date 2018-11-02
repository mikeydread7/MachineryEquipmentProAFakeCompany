package com.mybatis.demo.repository;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.MachineEquipmentPro;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MachineEquipmentPro.class)
public class IntegrationRepositoryImplTest implements MockTestList {

	@Resource
	private UserRepository userRepository;

	@Test
	public void testThatOurDBsequenceWorkedOnSaves() {
		String userName = "JohnBGood";
		User user = new User(0, userName, "blue", 70, 145, new Date(122L), 47);
		userRepository.insertUser(user);

		List<User> userSaved = userRepository.selectUserAllUsers();

		User userFound = userSaved.stream().filter(e -> e.getUserName().equals(userName)).findFirst().orElseGet(null);
		assertTrue(null != userFound && userFound.getUserName().equals(userName));
		/**
		 * This mean we are talking to a already populated the real H2 DB ad
		 * with a sequence defined our sequence is greater than 1, and we
		 */
		if (userSaved.size() > 1) {
			assertTrue(userFound.getUserId() > 1);
		}
	}

	@Test
	public void testSaved() {
		String userName = "JohnBBAD";
		User user = new User(userName, "brown", 50, 141, new Date(132L), 27);
		userRepository.insertUser(user);

		List<User> userSaved = userRepository.selectUserAllUsers();

		User userFound = userSaved.stream().filter(e -> e.getUserName().equals(userName)).findFirst().orElseGet(null);
		assertTrue(null != userFound && userFound.getUserName().equals(userName));
		/**
		 * This mean we are talking to a already populated the real H2 DB ad
		 * with a sequence defined our sequence is greater than 1, and we
		 */
		if (userSaved.size() > 1) {
			assertTrue(userFound.getUserId() > 1);
		}
	}

}
