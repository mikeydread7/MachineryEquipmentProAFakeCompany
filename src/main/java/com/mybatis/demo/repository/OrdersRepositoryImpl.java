package com.mybatis.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mybatis.demo.generics.dao.GenericMapperImpl;
import com.mybatis.demo.mapper.OrdersMapper;
import com.mybatis.demo.model.Orders;

@Repository
public class OrdersRepositoryImpl extends GenericMapperImpl<Orders,  Integer>  implements OrdersRepository {

	@Autowired
	private OrdersMapper ordersMapper;
	public OrdersRepositoryImpl(OrdersMapper ordersMapper) {
		this.ordersMapper =ordersMapper;
	}

	@Override
	public Orders findOne(Integer poid) {
		return ordersMapper.findOne(poid);
	}

	@Override
	public void save(Orders order, Integer poid) {
		ordersMapper.save(order, poid);

	}

	@Override
	public void insert(Orders order) {
		ordersMapper.insert(order);

	}
	
	@Override
	public void insert (Orders order, Object map){
		@SuppressWarnings("unchecked")
		Map<String,String> hp = (Map<String, String>) map;
		ordersMapper.insert(order, hp.get("productVersion"), hp.get("productName"));
	}
	
	@Override
	public void delete(Integer poid) {
		ordersMapper.delete(poid);

	}

	@Override
	public List<Orders> findAll() {
		return ordersMapper.findAll();
	}

}