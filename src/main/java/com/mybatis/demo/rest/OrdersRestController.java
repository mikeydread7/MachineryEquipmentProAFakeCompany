package com.mybatis.demo.rest;

import static com.mybatis.demo.constants.UtilContsants.API_VERSION;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import com.mybatis.demo.model.Orders;
import com.mybatis.demo.rest.hateosassembler.OrdersResourceAssembler;
import com.mybatis.demo.rest.response.AbstractListOfJson;
import com.mybatis.demo.rest.response.ModelResponse;
import com.mybatis.demo.service.OrdersService;
import com.mybatis.demo.utils.ValidateInput;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mybatis/order")
public class OrdersRestController {

	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(OrdersRestController.class);
	private final Gson gson = new GsonBuilder().create();
	private final ObjectMapper objectMapper = new ObjectMapper();
	// *************************************************************************
	// member variables
	private OrdersService ordersService;

	// *************************************************************************
	// Constructors
	public OrdersRestController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	// Implementation
	@ApiOperation(value = "Find a order by id", notes = "Retrive product information", response = Orders.class)
	@RequestMapping(value = "/id/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> getUserOrderById(@PathVariable Integer orderId) {
		LOG.info("findOrder: {}", orderId);
		Orders orders = ordersService.selectUserOrdesById(orderId);
		orders.add(linkTo(methodOn(OrdersRestController.class).getUserOrderById(orderId))
				.withSelfRel());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of orders", notes = "Retrive all orders information", response = AbstractListOfJson.class)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	public ResponseEntity<?> getAllUserOrders() {
		LOG.info("findAllOrders:");
		return new ResponseEntity<>(
				OrdersResourceAssembler.assembleLink(ordersService.selectAllUserOrders()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Add a new Order", httpMethod = "POST", notes = "Insert a new order")
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> insertNewProduct(@RequestBody Orders order)
			throws JsonParseException, JsonMappingException, IOException {

		String jsonLikeString = objectMapper.writeValueAsString(order);
		LOG.info("insert new Order: {}", jsonLikeString);
		ModelResponse obj = gson.fromJson("{}", ModelResponse.class);
		if (!ValidateInput.jsonCanBeTrusted(jsonLikeString) || null == jsonLikeString) {
			obj.setError(UtilContsants.INVALID_USER_OBJ);
			obj.setStatus(UtilContsants.FAILED);
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		ordersService.saveUserOrders(order);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
}
