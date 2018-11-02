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
import com.mybatis.demo.model.Products;
import com.mybatis.demo.rest.hateosassembler.ProductsResourceAssembler;
import com.mybatis.demo.rest.response.AbstractListOfJson;
import com.mybatis.demo.rest.response.ModelResponse;
import com.mybatis.demo.service.ProductsService;
import com.mybatis.demo.utils.ValidateInput;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mybatis/product")
public class ProductsRestController {

	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(ProductsRestController.class);
	private final Gson gson = new GsonBuilder().create();
	private final ObjectMapper objectMapper = new ObjectMapper();
	// *************************************************************************
	// member variables
	private ProductsService userProductsService;

	// *************************************************************************
	// Constructors
	public ProductsRestController(ProductsService userProductsService) {
		this.userProductsService = userProductsService;
	}

	// Implementation
	@ApiOperation(value = "Find a product by id", notes = "Retrive product Information", response = Products.class)
	@RequestMapping(value = "/id/{userProductId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> getUserProductsById(@PathVariable Integer userProductId) {
		LOG.info("findProduct: {}", userProductId);
		Products products = userProductsService.selectUserProductsById(userProductId);
		products.add(linkTo(
				methodOn(ProductsRestController.class).getUserProductsById(userProductId))
						.withSelfRel());
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of products", notes = "Retrive all products anformation", response = AbstractListOfJson.class)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	public ResponseEntity<?> getAllUserProducts() {
		LOG.info("findAllProducts:");
		return new ResponseEntity<>(ProductsResourceAssembler.assembleLink(
				userProductsService.selectAllUserProducts()), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a product", httpMethod = "POST", notes = "Insert a new Product")
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ API_VERSION)
	ResponseEntity<?> insertNewProduct(@RequestBody Products products)
			throws JsonParseException, JsonMappingException, IOException {

		String jsonLikeString = objectMapper.writeValueAsString(products);
		LOG.info("insert new product: {}", jsonLikeString);
		ModelResponse obj = gson.fromJson("{}", ModelResponse.class);
		if (!ValidateInput.jsonCanBeTrusted(jsonLikeString) || null == jsonLikeString) {
			obj.setError(UtilContsants.INVALID_USER_OBJ);
			obj.setStatus(UtilContsants.FAILED);
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		userProductsService.saveUserProducts(products);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
}
