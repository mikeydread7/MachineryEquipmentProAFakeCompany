package com.mybatis.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.model.Products;

@Mapper
public interface ProductMapper extends GenericMapper<Products, Integer> {
	static final String SELECT_STR = "productId, productName, productVersion, productView, productDesc";

	@Select("select " + SELECT_STR + " from USER_PRODUCTS where productId=#{pid}")
	Products findOne(@Param("pid")Integer pid);

	@Select("select " + SELECT_STR + " from USER_PRODUCTS")
	List<Products> findAll();

	default List<Products> defaultFindAll() {
		return findAll();
	}
	
	@Update("UPDATE USER_PRODUCTS SET "
			+ "productName=#{Product.productName}"
			+ ", productVersion=#{Product.productVersion}, productView=#{Product.productView}, productDesc=#{Product.productDesc}"	
	) 
	void save(@Param("Product") Products product, @Param("pid") Integer pid);
	
	@Update("INSERT INTO USER_PRODUCTS values "
			+ "("
			+ " user_products_id_seq.nextval, #{Product.productName}"
			+ ", #{Product.productVersion}, #{Product.productView}, #{Product.productDesc}"
			+ ")"
	)
	void insert(@Param("Product") Products product);
   
	@Delete("DELETE FROM USER_PRODUCTS where productId=#{id}")
	void delete(Integer pid); 
	
}
