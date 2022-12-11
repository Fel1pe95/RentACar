package model.dao;

import model.dao.implement.CarDaoJPA;
import model.db.DB;

public class DaoFactory {

	public static CarDaoJPA createCarDao() {
		return new CarDaoJPA(DB.getEntityManager());
	}

}
