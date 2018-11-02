package com.mybatis.demo.constants;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ActiveProfiles;

import com.mybatis.demo.model.Orders;
import com.mybatis.demo.model.Products;
import com.mybatis.demo.model.User;

@ActiveProfiles("h2")
@SuppressWarnings("serial")
public interface MockTestList {

	final List<User> mockEntityUserList = new ArrayList<User>() {
		{
			add(new User(1, "FOO", "brown", 3, 120, new Date(1003L), 3));
			add(new User(2, "BAR", "brown", 4, 150, new Date(1020L), 4));
			add(new User(3, "FOOBAR", "brown", 5, 170, new Date(1300L), 5));
			add(new User(4, "BARFOO", "brown", 6, 132, new Date(1050L), 6));
		}
	};

	final List<Products> mockEntityProductsList = new ArrayList<Products>() {
		{
			add(new Products(1, "IC Counterbalance Fork Lift","C-5 Series","c5-series.png","desc1"));
			add(new Products(2, "IC Counterbalance Fork Lift","C-G Series","cg-dosan.png","desc12"));
			add(new Products(3, "Reach Trucks Reaching","RR/RD-series","rr-series.png","desc13"));
			add(new Products(4, "Very Narrow Aisle Trucks","TSCP-series","tsp-series.pn","desc14"));
		}
	};

	final List<Orders> mockEntityOrderList = new ArrayList<Orders>() {
		{
			add(new Orders(1, 1, "String1", new Date(1L), new Date(2L), new Date(3L)));
			add(new Orders(2, 2, "String1", new Date(2L), new Date(3L), new Date(4L)));
			add(new Orders(3, 3, "String1", new Date(3L), new Date(4L), new Date(5L)));
			add(new Orders(4, 4, "String1", new Date(4L), new Date(5L), new Date(6L)));
		}
	};

	

	
}