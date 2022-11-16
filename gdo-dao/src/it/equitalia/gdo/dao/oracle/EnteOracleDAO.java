package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.services.interfaces.EnteDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EnteOracleDAO implements EnteDAOInterface  {
	
	private EntityManager entityManager;
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;

	}
	
	public Ente getTriplettaEnte(String chiaveUtente) {
		String sqlString = 	" select C_ENTE as codiceEnte, C_TIPO_UFF as tipoUfficio, C_UFFICIO as codiceUfficio " +
							" from WE1_CLIENTE " +
							" where C_CLIENTE_WEB = (select C_CLIENTE_WEB from WE1_SOGGETTO where USD = ?) ";
		
		Query q = entityManager.createNativeQuery(sqlString, Ente.class);
		q.setParameter(1, chiaveUtente);
		
		Ente e = (Ente) q.getSingleResult();
		
		
		return e;
	}

	public void delete(Serializable id) {
		//Stub di metodo generato automaticamente
		
	}

	public void delete(Ente t) {
		//Stub di metodo generato automaticamente
		
	}

	public Ente find(Serializable id) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List<Ente> findAll() {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List<Ente> findByParameter(Map<String, Object> params) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public Ente insert(Ente t) throws DataAccessException {
		//Stub di metodo generato automaticamente
		return null;
	}

	public Ente update(Ente t) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public String getTipologiaEnte(Ente ente) {
		//Metodo che estrae la tipologia da DB2; implementato in EnteDB2DAO.
		return null;
	}





}
