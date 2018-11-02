package com.mybatis.demo.repository;
import java.util.List;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.model.Orders;

public interface OrdersRepository extends GenericMapper<Orders, Integer> {
	
	Orders findOne(Integer poid);

	void save(Orders order, Integer poid);

	void insert (Orders order);
	
	void insert (Orders order, Object map);

	void delete(Integer id);

	List<Orders> findAll();
}
