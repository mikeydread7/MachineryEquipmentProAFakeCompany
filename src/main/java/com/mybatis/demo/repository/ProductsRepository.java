package com.mybatis.demo.repository;

import java.util.List;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.model.Products;

public interface ProductsRepository extends GenericMapper<Products, Integer>{

	Products findOne(Integer pid);

	void save(Products user, Integer poid);

	void insert(Products products);
	
	void insert (Products products, Object map);
	
	void delete(Integer id);

	List<Products> findAll();

}