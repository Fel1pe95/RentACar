package model.dao;

import java.util.List;

public interface CarDAO {

	void insert(Object obj);
	void update(Object obj);
	void removeById(Object obj);
	Object findById(Integer id);
	List<Object> findAll();
	
}
