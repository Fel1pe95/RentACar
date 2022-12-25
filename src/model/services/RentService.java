package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.RentDAO;
import model.entities.Rent;

public class RentService {

	private RentDAO dao = DaoFactory.createRentDao();
	
	public void saveArent(Rent rent) {
		dao.insert(rent);
	}
	
	public void removeRent(Integer id) {
		dao.removeById(id);
	}
	
	public Rent findById(Integer id) {
		return dao.findById(id);
	}
	
	public List<Rent> findAll(){
		return dao.findAll();
	}
}
