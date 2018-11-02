package com.mybatis.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.model.Orders;


@Mapper
public interface OrdersMapper extends GenericMapper<Orders, Integer> {
	static final String SELECT_STR = "productOrderId, productId, productUserId, productAmount, productOrderedDate,productSaleDate, productShippedDate";

	@Select("select " + SELECT_STR + " from USER_ORDERS where productOrderId=#{poid}")
	Orders findOne(@Param("poid")Integer poid);
	
	
	

	@Select("select " + SELECT_STR + " from USER_ORDERS")
	List<Orders> findAll();

	
	default List<Orders> defaultFindAll() {
		return findAll();
	}
    
	@Update("UPDATE USER_ORDERS SET"
			+ "productAmount=#{Orders.productAmount}"
			+ ", productOrderedDate=#{Orders.productOrderedDate}, productSaleDate=#{Orders.productSaleDate}"
			+ ", productShippedDate=#{Order.productShippedDate} where productOrderId=#{poid}"
	) 
	void save(@Param("Order")Orders order, @Param("poid")Integer poid);
	
	/**
	 * TODO make correct implementation
     *     order1--- user = 3 orders 1 to many 
	 *    /      /  /
	 * P /order2---/--- user 1 order
	 *   \        /
	 *    \      /
	 *     order3---- user
	 *  
	 */
	@Update("INSERT INTO USER_ORDERS values "
			+ "("
			+ " user_products_order_id_seq.nextval"
			+ ", (select productId from USER_PRODUCTS where productName='#{poin}' and productVersion='#{pois}')"
			+ ", #{Order.productUserId} #{Order.productAmount},#{Orders.productOrderedDate}"
			+ ", #{Orders.productSaleDate}, #{Order.productShippedDate}"
			+ ")"
	)
	void insert(@Param("Order") Orders order,  @Param("pois")String productVersion, @Param("poin") String productName);
   
	@Delete("DELETE FROM from USER_ORDERS where productOrderId =#{poid}")
	void delete(@Param("poid")Integer poid);

	void insert(Orders order); 
}