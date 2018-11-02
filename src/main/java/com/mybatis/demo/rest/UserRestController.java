package com.mybatis.demo.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import static com.mybatis.demo.constants.UtilContsants.*;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mybatis.demo.constants.UtilContsants;
import com.mybatis.demo.model.User;
import com.mybatis.demo.rest.hateosassembler.UserResourceAssembler;
import com.mybatis.demo.rest.response.AbstractListOfJson;
import com.mybatis.demo.rest.response.ModelResponse;
import com.mybatis.demo.service.UserService;

import com.mybatis.demo.utils.ValidateInput;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mybatis/user")
public class UserRestController {

	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(UserRestController.class);
	private final Gson gson = new GsonBuilder().create();
	private final ObjectMapper objectMapper = new ObjectMapper();
	// *************************************************************************************************
	// member variables
	private UserService userService;

	// *************************************************************************************************
	// Constructors
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	// Implementation
	@ApiOperation(value = "Find a user by id", notes = "Retrive an user information", response = User.class)
	@RequestMapping(value = "/id/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> getUser(@PathVariable Integer userId) {
		LOG.info("findUser: {}", userId);
		User user = userService.selectUserById(userId);
		user.add(
				linkTo(methodOn(UserRestController.class).getUser(userId)).withSelfRel());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of users", notes = "Retrive all user information", response = AbstractListOfJson.class)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	public ResponseEntity<?> getAllUser() {
		LOG.info("findAllUser:");
		return new ResponseEntity<>(
				UserResourceAssembler.assembleLink(userService.selectAllUsers()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "delete user by id", notes = "delete a user by id")
	@RequestMapping(value = "/id/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
		LOG.info("deletUser: {}", userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Add a user", httpMethod = "POST", notes = "Insert a new user")
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> insertNewUser(@RequestBody User user)
			throws JsonParseException, JsonMappingException, IOException {

		String jsonLikeString = objectMapper.writeValueAsString(user);
		LOG.info("insert new user: {}", jsonLikeString);
		ModelResponse obj = gson.fromJson("{}", ModelResponse.class);
		if (!ValidateInput.jsonCanBeTrusted(jsonLikeString) || null == jsonLikeString) {
			obj.setError(UtilContsants.INVALID_USER_OBJ);
			obj.setStatus(UtilContsants.FAILED);

			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		userService.saveUser(user);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an user", httpMethod = "PUT", notes = "Update an user")
	@RequestMapping(value = "/id/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> saveOrUpdateUser(@RequestBody User user,
			@PathVariable("userId") Integer userId)
			throws JsonParseException, JsonMappingException, IOException {

		String jsonLikeString = objectMapper.writeValueAsString(user);
		LOG.info("save or update: {}", jsonLikeString);
		ModelResponse obj = gson.fromJson("{}", ModelResponse.class);
		if (!ValidateInput.jsonCanBeTrusted(jsonLikeString) || null == jsonLikeString) {
			obj.setError(UtilContsants.INVALID_USER_ID);
			obj.setStatus(UtilContsants.FAILED);
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		userService.updateUser(user, userId);
		return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);
	}

}
