package com.mybatis.demo.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.utils.RESTFulHelper;
import com.mybatis.demo.utils.ValidateInput;

public class RESTFulHelperTest {

	@Test
	public void testRestFullHelperISODATE() throws JsonProcessingException {
		assertTrue(
				RESTFulHelper.getISOTimeStamp(MockTestList.mockEntityUserList.get(2).getBirthday()).contains("Z"));
	}

	@Test
	public void testRestFullHelperGotGoodJSON() throws JsonProcessingException {
		assertTrue(ValidateInput
				.jsonCanBeTrusted(RESTFulHelper.getJsonLikeString(MockTestList.mockEntityUserList.get(3))));
	}
}
