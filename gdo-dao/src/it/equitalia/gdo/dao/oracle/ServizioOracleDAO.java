package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Servizio;
import it.equitalia.gdo.dao.services.interfaces.ServizioDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServizioOracleDAO implements ServizioDAOInterface  {
	
	private EntityManager entityManager;
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;

	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListaServiziAttiviUtenteEnteoAgente (String chiaveUtente) {
		String sqlString = 	" select DISTINCT sa.ACRONIMO as ID_SERVIZIO " +
							" from WE1_SOGG_APPL sa " +  
						   	" where sa.C_CLIENTE_WEB = ( select C_CLIENTE_WEB from WE1_SOGGETTO SOGG where SOGG.USD = ? ) " +
							" AND sa.FLAG_ATTIVAZIONE=3 " + 
							" AND sa.D_ELAB is not null " +
							" order by ID_SERVIZIO";
		
		Query q = entityManager.createNativeQuery(sqlString);
		q.setParameter(1, chiaveUtente);
		
		return q.getResultList();
	}


	
	public List<Servizio> getListaServiziEnte() {
		// Metodo che va su DB2; implementato in ServizioDB2DAO.
		return null;
	}

	public List<Servizio> getListaServiziAgente() {
		// Metodo che va su DB2; implementato in ServizioDB2DAO.
		return null;
	}
	
	public List<Servizio> getListaServiziAltriUtenti() {
		// TODO Stub di metodo generato automaticamente
		return null;
	}

	public void delete(Serializable id) {
		//Stub di metodo generato automaticamente		
	}


	public void delete(Servizio t) {
		//Stub di metodo generato automaticamente		
	}


	public Servizio find(Serializable id) {
		//Stub di metodo generato automaticamente
		return null;
	}


	public List<Servizio> findAll() {
		//Stub di metodo generato automaticamente
		return null;
	}


	public List<Servizio> findByParameter(Map<String, Object> params) {
		//Stub di metodo generato automaticamente
		return null;
	}


	public Servizio insert(Servizio t) throws DataAccessException {
		//Stub di metodo generato automaticamente
		return null;
	}


	public Servizio update(Servizio t) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List<String> getListaServiziAttiviAltriUtenti(String chiaveUtente) {
		// TODO Stub di metodo generato automaticamente
		return null;
	}
	
	public List<Servizio> getListaServiziUtenteEsterno() {
		// TODO Stub di metodo generato automaticamente
		return null;
	}

	@Override
	public List<String> getListaServiziAttiviUtenteEsterno(String usd) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
