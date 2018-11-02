package com.mybatis.demo.generics.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericMapperImpl<E, K extends Serializable>  implements GenericMapper<E, K> {
	
	@Autowired
    protected GenericMapper<E, K> genericMapper;
	
	public GenericMapperImpl() {
	}
	
	
	@Override
	public E findOne(K key){
		return genericMapper.findOne(key);
	}
	@Override
	public List<E> findAll(){
		return genericMapper.findAll();
	}
	@Override
	public void save(E entity, K key){
		 genericMapper.save(entity, key);
	}
	@Override
	public void insert(E entity){
		 genericMapper.insert(entity);
	}
	@Override
	public void delete(K key){
		 genericMapper.delete(key);
	}
}
