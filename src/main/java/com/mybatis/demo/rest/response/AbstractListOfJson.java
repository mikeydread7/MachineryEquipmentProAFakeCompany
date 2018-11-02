package com.mybatis.demo.rest.response;

import java.util.List;

public class AbstractListOfJson<T> implements ListOfJson<T> {

	private List<T> listOfElements;

	public AbstractListOfJson() {
		super();
	}

	@Override
	public List<T> getlistOfElements() {
		return this.listOfElements;
	}

	@Override
	public void setlistOfElements(List<T> e) {
		this.listOfElements = e;
	}
}
