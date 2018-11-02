package com.mybatis.demo.service;

import java.util.List;

import com.mybatis.demo.generics.service.GenericService;
import com.mybatis.demo.model.Products;

public interface ProductsService  extends GenericService<Products, Integer> {

	Products selectUserProductsById(Integer pid);

	void saveUserProducts(Products products);

	void deleteUserProducts(Integer pid);

	void updateUserProducts(Products products, Integer pid);

	List<Products> selectAllUserProducts();

	Boolean exists(Products products);

}