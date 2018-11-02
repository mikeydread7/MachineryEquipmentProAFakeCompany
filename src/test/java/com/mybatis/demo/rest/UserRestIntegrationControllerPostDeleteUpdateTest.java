package com.mybatis.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatis.demo.MachineEquipmentPro;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.mapper.UserMapper;
import com.mybatis.demo.model.User;
import com.mybatis.demo.repository.UserRepository;
import com.mybatis.demo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MachineEquipmentPro.class)
public class UserRestIntegrationControllerPostDeleteUpdateTest implements MockTestList {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Mock
	private UserMapper userMapper;
	@Mock
	private UserRepository userRepository ;
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserRestController userRestController;

	private final String apiRoot = "/mybatis/user";
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Before
	public void init() {
		when(userMapper.findAll()).thenReturn(mockEntityUserList);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPostAUser() throws Exception {
		User user = new User(5, "userName", "black", 60, 120, new Date(200L), 45);
		String jsonLikeString = objectMapper.writeValueAsString(user);
		logger.info("{}", jsonLikeString);
		assertThat(this.userService).isNotNull();
		mockMvc.perform(post(apiRoot.concat("/")).contentType(MediaType.APPLICATION_JSON).content(jsonLikeString))
				.andExpect(status().isCreated());
	}

	@Test
	public void testToPutAUser() throws Exception {
		Integer userId = 1;
		User user = new User(5, "userName", "black", 60, 120, new Date(200L), 45);
		String jsonLikeString = objectMapper.writeValueAsString(user);
		logger.info("{}", jsonLikeString);
		assertThat(this.userService).isNotNull();
		mockMvc.perform(put(apiRoot.concat("/id/".concat(userId.toString()))).contentType(MediaType.APPLICATION_JSON)
				.content(jsonLikeString)).andExpect(status().isAccepted());
	}

}
