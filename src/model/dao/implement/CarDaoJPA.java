package model.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.dao.CarDAO;
import model.entities.Car;
import model.exceptions.DbException;
import model.exceptions.DbIntegrityException;
import model.exceptions.ValidationException;

public class CarDaoJPA implements CarDAO {

	private EntityManager manager;

	public CarDaoJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void insert(Car obj) {
		try {
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
		} catch (DbException e) {
			manager.getTransaction().rollback();
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		} catch (ValidationException e) {
			manager.getTransaction().rollback();
			throw new ValidationException("Erro ao registrar carro" + e.getMessage());
		}

	}

	@Override
	public void update(Car car) {
		Car newCar = findById(car.getId());
		newCar = updateCar(car, newCar);

		try {
			manager.getTransaction().begin();
			manager.merge(newCar);
			manager.getTransaction().commit();

		} catch (DbException e) {
			manager.getTransaction().rollback();
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		} catch (DbIntegrityException e) {
			manager.getTransaction().rollback();
			throw new DbIntegrityException("Erro ao atualizar carro" + e.getMessage());
		}

	}

	@Override
	public void removeById(Integer id) {
		Object obj = findById(id);
		try {
			manager.getTransaction().begin();
			manager.remove(obj);
			manager.getTransaction().commit();
		} catch (DbException e) {
			manager.getTransaction().rollback();
			throw new DbException("Erro ao conectar banco de dados");
		} catch (DbIntegrityException e) {
			manager.getTransaction().rollback();
			throw new DbIntegrityException("Erro ao remover registro do carro" + e.getMessage());
		}

	}

	@Override
	public Car findById(Integer id) {
		Car car = new Car();
		try {
			car = manager.find(Car.class, id);
		} catch (DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}
		return car;
	}

	@Override
	public List<Car> findAll() {
		List<Car> list = new ArrayList<>();
		try {
			TypedQuery<Car> consulta = manager.createQuery("Select a from Car a", Car.class);
			list = consulta.getResultList();
		} catch (DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}
		return list;
	}

	public Car updateCar(Car car, Car newCar) {
		newCar = new Car();
		newCar.setId(car.getId());
		newCar.setModel(car.getModel());
		newCar.setHourlyValue(car.getHourlyValue());
		return newCar;
	}

}
