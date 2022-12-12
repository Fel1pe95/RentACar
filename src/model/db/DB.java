package model.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.exceptions.DbException;

public class DB {

	private static EntityManagerFactory factory = null;
	private static EntityManager manager = null;

	// cria e retorna o factory;
	private static EntityManagerFactory getFactory() {
		try {
			factory = Persistence.createEntityManagerFactory("rentacar");
		}catch(DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
			
		}
		
		return factory;
	}

	public static EntityManager getEntityManager() {

		// cria e retorna o entity manager
		try {
			factory = getFactory();
			manager = factory.createEntityManager();
		} catch (DbException e) {
			throw new DbException("Erro ao conectar banco de dados" + e.getMessage());
		}

		return manager;

	}

	// fecha a conexao
	public static void closeConection() {
		getEntityManager().close();
		getFactory().close();
	}

}
