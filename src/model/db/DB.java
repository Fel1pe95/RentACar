package model.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DB {

	private static EntityManagerFactory factory = null;
	private static EntityManager manager = null;

	// cria e retorna o factory;
	private static EntityManagerFactory getFactory() {
		factory = Persistence.createEntityManagerFactory("rentacar");
		return factory;
	}

	public static EntityManager getEntityManager() {

		// cria e retorna o entity manager
		try {
			factory = getFactory();
			manager = factory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return manager;

	}

	// fecha a conexao
	public static void closeConection() {
		getEntityManager().close();
		getFactory().close();
	}

}
