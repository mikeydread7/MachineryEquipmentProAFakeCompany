package com.mybatis.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybatis.demo.constants.MockTestList;

public class OrderServiceTest {
	

	@Mock
	private  OrdersService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
    public void testMockCreation(){
        assertNotNull(orderService);
    }

	@Test
	public void testAll() {
		when(orderService.findAll()).thenReturn(MockTestList.mockEntityOrderList);
		assertTrue(orderService.findAll().size() > 1);
		verify(orderService, times(1)).findAll();
	}

	@Test
	public void testById() {
		Integer orid = 2;
		when(orderService.findOne(orid)).thenReturn(MockTestList.mockEntityOrderList.stream()
				.filter(e -> e.getProductOrderId().intValue() == orid).findFirst().orElse(null));
		assertTrue(orderService.findOne(orid).getProductOrderId().equals(orid));
		verify(orderService, times(1)).findOne(orid);
	}

	
}
