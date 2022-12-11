package model.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.dao.CarDAO;
import model.entities.Car;

public class CarDaoJPA implements CarDAO{

	private EntityManager manager;
	
	public CarDaoJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void insert(Object obj) {
		try {
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
		}catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object findById(Integer id) {
		Object obj = new Object();
		try {
			obj = manager.find(Car.class, id);	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<Object> findAll() {
		List<Object> list = new ArrayList<>();
		try {
			TypedQuery<Object> consulta = manager.createQuery("Select a from " + Object.class.getName() + " a", Object.class);
			list = consulta.getResultList();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
