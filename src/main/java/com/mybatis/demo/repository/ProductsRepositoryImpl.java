package com.mybatis.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mybatis.demo.generics.dao.GenericMapperImpl;
import com.mybatis.demo.mapper.ProductMapper;
import com.mybatis.demo.model.Products;

@Repository("productsRepositoryImpl")
public class ProductsRepositoryImpl  extends GenericMapperImpl<Products, Integer> implements ProductsRepository{

	
	@Autowired
	private ProductMapper productMapper;
	
	public ProductsRepositoryImpl( ProductMapper productMapper)  {
		this.productMapper = productMapper;
	}

	@Override
	public Products findOne(Integer pid) {
		return  productMapper.findOne(pid);
	}

	@Override
	public void save(Products products, Integer poid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Products products) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert (Products products, Object map){
		 throw new RuntimeException("not supported");

	}
	@Override
	public void delete(Integer id) {
		productMapper.delete(id);
		
	}

	@Override
	public List<Products> findAll() {
		return  productMapper.findAll();
	}

}
