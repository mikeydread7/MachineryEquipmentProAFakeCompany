package com.mybatis.demo.rest.hateosassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import com.mybatis.demo.model.Orders;
import com.mybatis.demo.rest.OrdersRestController;

public class OrdersResourceAssembler {

	public static List<Orders> assembleLink(List<Orders> jsonPayLoad) {

		jsonPayLoad.stream().forEach(e -> e.add(linkTo(methodOn(OrdersRestController.class)
				.getAllUserOrders()).withSelfRel()));
		jsonPayLoad.stream().forEach( e -> e.add(linkTo(methodOn(OrdersRestController.class)
				.getAllUserOrders()).slash("id").slash(e.getProductOrderId()).withRel("id")));
		
		return jsonPayLoad;
	}
	


}