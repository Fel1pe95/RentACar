package model.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.dao.RentDAO;
import model.entities.Rent;
import model.exceptions.DbException;
import model.exceptions.DbIntegrityException;
import model.exceptions.ValidationException;

public class RentDaoJPA implements RentDAO{

	private EntityManager manager;
	
	public RentDaoJPA(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void insert(Rent obj) {
		try {
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();
		}catch(DbException e) {
			manager.getTransaction().rollback();
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}catch(ValidationException e) {
			manager.getTransaction().rollback();
			throw new ValidationException("Erro ao registrar aluguel" + e.getMessage());
		}
	}

	@Override
	public void update(Rent obj) {
		
		
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
	public Rent findById(Integer id) {
		Rent rent = new Rent();
		try {
			rent = manager.find(Rent.class, id);
		} catch (DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}
		return rent;
	
	}

	@Override
	public Rent findByClientCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rent> findAll() {
		List<Rent> list = new ArrayList<>();
		try {
			TypedQuery<Rent> consulta = manager.createQuery("Select a from Rent a", Rent.class);
			list = consulta.getResultList();
		}catch(DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}
		return list;
	}
	
	
}
