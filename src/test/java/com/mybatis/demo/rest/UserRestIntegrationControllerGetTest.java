package com.mybatis.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mybatis.demo.MachineEquipmentPro;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.mapper.UserMapper;
import com.mybatis.demo.repository.UserRepository;
import com.mybatis.demo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MachineEquipmentPro.class)
public class UserRestIntegrationControllerGetTest implements MockTestList {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Mock
	private UserMapper userMapper;
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserService userService;

	@InjectMocks
	private UserRestController userRestController;

	private final String apiRoot = "/mybatis/user";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(userMapper.findAll()).thenReturn(mockEntityUserList);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAllUsers() throws Exception {

		assertThat(this.userService).isNotNull();
		mockMvc.perform(get(apiRoot).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testFindUser() throws Exception {
		Integer userId = 2;

		assertThat(this.userService).isNotNull();
		mockMvc.perform(get(apiRoot.concat("/id/").concat(userId.toString())).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.userId", is((userId))))
				.andExpect(jsonPath("$.userName", is("Steffan"))).andExpect(jsonPath("$.eyeColor", is("brown")))
				.andExpect(jsonPath("$.weight", is(89))).andDo(print());
	}
}
