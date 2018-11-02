package com.mybatis.demo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mybatis.demo.constants.MockTestList;
import com.mybatis.demo.model.Products;
import com.mybatis.demo.service.ProductsService;

@RunWith(MockitoJUnitRunner.class)
public class ProductsRestControllerTest {

	@Mock
	private HttpServletRequest mockHttpServletRequest;
	@Mock
	private ProductsService userProductsService;

	@InjectMocks
	private ProductsRestController userProductsRestController;

	@Before
	public void setup() {
		HttpServletRequest mockRequest = new MockHttpServletRequest();
		ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
		RequestContextHolder.setRequestAttributes(servletRequestAttributes);
	}

	@After
	public void teardown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAllUsers() {
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/product");
		when((userProductsService.selectAllUserProducts())).thenReturn(MockTestList.mockEntityProductsList);
		ResponseEntity<?> results = userProductsRestController.getAllUserProducts();
		assertTrue("Found values", ((List<Products>) results.getBody()).size() == 4);
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}
    
	@Test
	public void testFindProducts() {

		Integer userProductId = 3;
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/product/id/".concat(Integer.toString(userProductId)));
		when((userProductsService.selectUserProductsById(userProductId))).thenReturn(find(userProductId));
		ResponseEntity<?> results = userProductsRestController.getUserProductsById(userProductId);
		assertTrue("Found values", ((Products) results.getBody()).getProductId().equals(userProductId));
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}


	// *************************************************************************************************
	// Utility
	private Products find(Integer userProductId) {

		return MockTestList.mockEntityProductsList.stream()
				.filter(p->p.getProductId().equals( userProductId))
				.findFirst()
				.orElse(new Products());
	}
}
