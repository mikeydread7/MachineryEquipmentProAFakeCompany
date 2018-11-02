package com.mybatis.demo.rest.hateosassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import com.mybatis.demo.model.User;
import com.mybatis.demo.rest.UserRestController;


public class UserResourceAssembler {

	public static List<User> assembleLink(List<User> jsonPayLoad) {
		
		jsonPayLoad.stream().forEach(e -> e.add(linkTo(methodOn(UserRestController.class)
				.getAllUser()).withSelfRel()));
		jsonPayLoad.stream().forEach(e -> e.add(linkTo(methodOn(UserRestController.class)
				.getAllUser()).slash("id").slash(e.getUserId()).withRel("id")));
		
		return jsonPayLoad;
	}

}