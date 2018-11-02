package com.mybatis.demo.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.MachineEquipmentPro;
import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.Orders;
import com.mybatis.demo.repository.OrdersRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MachineEquipmentPro.class)
public class OrdersServiceIntegrationTest implements MockTestList {

	@Mock
	private OrdersRepository orderRepository;
	@Mock
	private OrdersService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		orderService = new OrdersServiceImpl(orderRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAll() {
		when(orderRepository.findAll()).thenReturn(mockEntityOrderList);
		assertTrue(orderService.findAll().size() > 1);
	}

	@Test
	public void testById() {
		Integer orid = 2;
		when(orderRepository.findOne(orid)).thenReturn(
				mockEntityOrderList.stream().filter(e -> e.getProductOrderId().intValue() == orid).findFirst().orElse(null));
		assertTrue(orderService.findOne(orid).getProductOrderId().equals(2));
	}

	@Test
	public void testInsert() {

		Orders order = new Orders(1, 3, "String-120", new Date(100L),new Date(100L),new Date(10L)) ;
		Map<String, String>map = new HashMap<>();
		map.put("productVersion", "productVersion-1.00");
		map.put("productName","The-TRUCK");
		orderRepository.insert(order, map);
		verify(orderRepository, never()).delete(1);
	}

	@Test
	public void testDelete() {

		orderRepository.delete(1);
		verify(orderRepository, never()).findOne(4);
		verify(orderRepository, never()).delete(4);
	}

}