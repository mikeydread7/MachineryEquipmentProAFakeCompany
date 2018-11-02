package com.mybatis.demo.generics.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.generics.dao.GenericMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

	private GenericMapper<E, K> genericDao;
    public GenericServiceImpl(GenericMapper<E,K> genericDao) {
        this.genericDao=genericDao;
    }
    public GenericServiceImpl() {
    }

	@Override
	public E findOne(K key) {
		return genericDao.findOne(key);
	}

	@Override
	public void insert(E entity) {
		genericDao.insert(entity);
		
	}

	@Override
	public void delete(K key) {
		genericDao.delete(key);
		
	}

	@Override
	public void save(E entity, K key) {
		genericDao.save(entity, key);
		
	}

	@Override
	public List<E> findAll() {
		return genericDao.findAll();
	}


}
