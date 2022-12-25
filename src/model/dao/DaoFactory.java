package model.dao;

import model.dao.implement.CarDaoJPA;
import model.dao.implement.RentDaoJPA;
import model.db.DB;

public class DaoFactory {

	public static CarDaoJPA createCarDao() {
		return new CarDaoJPA(DB.getEntityManager());
	}
	
	public static RentDaoJPA createRentDao() {
		return new RentDaoJPA(DB.getEntityManager());
	}

}
