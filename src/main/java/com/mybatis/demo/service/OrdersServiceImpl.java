package com.mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.generics.service.GenericServiceImpl;
import com.mybatis.demo.model.Orders;
import com.mybatis.demo.repository.OrdersRepository;


@Service
public class OrdersServiceImpl extends GenericServiceImpl<Orders,Integer> implements OrdersService {
	
	private OrdersRepository orderRepository;
	
	@Autowired
	public OrdersServiceImpl(@Qualifier("ordersRepositoryImpl")GenericMapper<Orders, Integer> orderRepository) {
		super(orderRepository);
		this.orderRepository = (OrdersRepository) orderRepository;
	}

	@Override
	public Orders selectUserOrdesById(Integer poid) {
		return orderRepository.findOne(poid);
	}

	@Override
	public void saveUserOrders(Orders order) {
		orderRepository.insert(order);

	}
	
	@Override
	public void insert (Orders order, Object map){
		orderRepository.insert(order, map);
	}
	
	@Override
	public void deleteUserOrders(Integer poid) {
		orderRepository.delete(poid);

	}

	@Override
	public void updateUserOrders(Orders order, Integer poid) {
		orderRepository.save(order, poid);

	}

	@Override
	public List<Orders> selectAllUserOrders() {
	
		return orderRepository.findAll();
	}

	@Override
	public Boolean exists(Orders order) {
		return (null == orderRepository.findOne(order.getProductOrderId()));
	}

}
