package model.dao;

import java.util.List;

import model.entities.Car;

public interface CarDAO {

	void insert(Car obj);
	void update(Car obj);
	void removeById(Integer id);
	Car findById(Integer id);
	List<Car> findAll();
	
}
