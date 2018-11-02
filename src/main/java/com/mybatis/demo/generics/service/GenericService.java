package com.mybatis.demo.generics.service;

import java.util.List;


public interface GenericService <E, K> {
	
	E findOne(K key);
	void insert(E entity);
	void delete(K key);
	void save(E entity, K key);
	List<E> findAll();
}
