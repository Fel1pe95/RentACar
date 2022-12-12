package model.services;

import java.util.List;

import model.dao.CarDAO;
import model.dao.DaoFactory;
import model.entities.Car;

public class CarService {

	private CarDAO dao = DaoFactory.createCarDao();
	
	public void saveAcar(Car car) {
		dao.insert(car);
	}
	
	public void updateAcar(Car car) {
		dao.update(car);
	}
	
	public void removeCar(Integer id) {
		dao.removeById(id);
	}
	
	public Car findById(Integer id) {
		return dao.findById(id);	
	}
	
	public List<Car> findAll() {
		return dao.findAll();
	}
}
