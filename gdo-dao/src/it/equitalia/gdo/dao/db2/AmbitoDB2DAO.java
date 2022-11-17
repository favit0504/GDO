package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ambito;
import it.equitalia.gdo.dao.services.interfaces.AmbitoDAOInterface;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AmbitoDB2DAO  implements AmbitoDAOInterface {
	
	
	private EntityManager entityManager;
	
	private static final String queryAmbiti = "SELECT C_CONCESSIONE,S_DENOM_CONC FROM SIG.SIGT2_CONCESSIONE WHERE C_COD_LINGUA = 'ITL' AND D_VAL_INF = '9999-12-31' AND D_SCAD_CONC >= CURRENT DATE"
												+" AND C_CONCESSIONE <> 300 order by S_DENOM_CONC";
	
	
	
	
	public void delete(Serializable id) {
		//Stub di metodo generato automaticamente		
	}

	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}


	@SuppressWarnings("unchecked")
	public List<Ambito> getListaAmbitiAttivi() {
		Query q = entityManager.createNativeQuery(queryAmbiti, Ambito.class);		
		
		return q.getResultList();
	}


	public void delete(Ambito t) {
		// TODO Auto-generated method stub
		
	}


	public Ambito insert(Ambito t) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	public Ambito update(Ambito t) {
		// TODO Auto-generated method stub
		return null;
	}



	public Ambito find(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Ambito> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Ambito> findByParameter(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
