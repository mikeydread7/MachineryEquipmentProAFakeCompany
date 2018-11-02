package com.mybatis.demo.service;

import java.util.List;

import com.mybatis.demo.generics.service.GenericService;
import com.mybatis.demo.model.Orders;

public interface OrdersService extends GenericService<Orders, Integer> {

	Orders selectUserOrdesById(Integer poid);

	void saveUserOrders(Orders order);

	void insert (Orders order, Object map);
	
	void deleteUserOrders(Integer poid);

	void updateUserOrders(Orders order, Integer poid);

	List<Orders> selectAllUserOrders();

	Boolean exists(Orders products);

}