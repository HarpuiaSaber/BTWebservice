package webservice.BHXH.service;

import webservice.BHXH.exception.InternalServerException;

import java.util.List;

public interface BaseService<T, K> {

	void add(T t) throws InternalServerException;

	void update(T t);

	void delete(K k);

	T getById(K k);
	
	List<T> getAll();
}
