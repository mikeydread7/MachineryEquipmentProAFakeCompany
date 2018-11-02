package com.mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mybatis.demo.generics.dao.GenericMapper;
import com.mybatis.demo.generics.service.GenericServiceImpl;
import com.mybatis.demo.model.Products;
import com.mybatis.demo.repository.ProductsRepository;

@Service
public class ProductsServiceImpl  extends GenericServiceImpl<Products, Integer> implements ProductsService {

	private ProductsRepository  productsRepository;
	
	public ProductsServiceImpl(@Qualifier("productsRepositoryImpl")GenericMapper<Products, Integer>  productsRepository) {
	    super(productsRepository);
		this.productsRepository = (ProductsRepository) productsRepository;
	}

	@Override
	public Products selectUserProductsById(Integer pid) {
		return productsRepository.findOne(pid);
	}

	@Override
	public void saveUserProducts(Products products) {

	}

	@Override
	public void deleteUserProducts(Integer pid) {
		productsRepository.delete(pid);

	}

	@Override
	public void updateUserProducts(Products products, Integer pid) {
		
		productsRepository.save(products, pid);
	}

	@Override
	public List<Products> selectAllUserProducts() {
		
		 return productsRepository.findAll();
	}

	@Override
	public Boolean exists(Products products) {
		 return (null == productsRepository.findOne(products.getProductId()));
	}

}
