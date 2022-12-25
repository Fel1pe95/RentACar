package model.dao;

import java.util.List;

import model.entities.Rent;

public interface RentDAO {

	void insert(Rent obj);
	void update(Rent obj);
	void removeById(Integer id);
	Rent findById(Integer id);
	Rent findByClientCpf(String cpf);
	List<Rent> findAll();
}
