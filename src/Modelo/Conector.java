package Modelo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Conector {

	private EntityManager em = null;

	public Conector() {
		try {
			
			em = Persistence.createEntityManagerFactory("CHAT").createEntityManager();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void cerrar() {
		try {
			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	
}
