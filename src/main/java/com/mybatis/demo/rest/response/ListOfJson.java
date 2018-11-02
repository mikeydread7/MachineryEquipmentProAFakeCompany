package com.mybatis.demo.rest.response;

import java.util.List;

public interface ListOfJson<T> {

	List<T> getlistOfElements();

	void setlistOfElements(List<T> e);

}