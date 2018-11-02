package com.mybatis.demo.rest.hateosassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import com.mybatis.demo.model.Products;
import com.mybatis.demo.rest.ProductsRestController;

public class ProductsResourceAssembler {

	public static List<Products> assembleLink(List<Products> jsonPayLoad) {

		jsonPayLoad.stream().forEach(e -> e.add(linkTo(methodOn(ProductsRestController.class).getAllUserProducts()).withSelfRel()));
		jsonPayLoad.stream().forEach( e -> e.add(linkTo(methodOn(ProductsRestController.class).getAllUserProducts())
				.slash("id").slash(e.getProductId())
				.withRel("id")));
		jsonPayLoad.stream().forEach( e -> e.add(linkTo(methodOn(ProductsRestController.class).getAllUserProducts())
				.slash("id")
				.slash(e.getProductId())
				.slash("series")
				.slash(e.getProductVersion())
				.withRel("series")));
		return jsonPayLoad;
	}

	
}