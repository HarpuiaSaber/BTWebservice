package webservice.BHXH.service;

import java.util.List;

public interface BaseService<T, K> {

	void add(T t);

	void update(T t);

	void delete(K k);

	T getById(K k);
	
	List<T> getAll();
}
