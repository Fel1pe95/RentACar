package model.services;

import java.util.List;

import model.dao.CarDAO;
import model.dao.DaoFactory;

public class CarService {

	private CarDAO dao = DaoFactory.createCarDao();
	
	public void saveAcar(Object obj) {
		dao.insert(obj);
	}
	
	public List<Object> findAll() {
		return dao.findAll();
	}
}
