package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Servizio;
import it.equitalia.gdo.dao.services.interfaces.ServizioDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServizioDB2DAO implements ServizioDAOInterface {

	private static final String PARAM_CODICE_SERVIZIO_NEW = "c_servizio_new";
	private static final String ENTE = "E";
	private static final String AGENTE = "A";
	
	private static final String whereServizioEnte = " where s.codiceServizioNew = :"+PARAM_CODICE_SERVIZIO_NEW;
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<Servizio> getListaServiziEnte() {
		Query q = entityManager.createQuery("select s from Servizio s " +whereServizioEnte + " order by s.servizio ");
		q.setParameter(PARAM_CODICE_SERVIZIO_NEW, ENTE);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Servizio> getListaServiziAgente() {
		Query q = entityManager.createQuery("select s from Servizio s " +whereServizioEnte + " order by s.servizio ");
		q.setParameter(PARAM_CODICE_SERVIZIO_NEW, AGENTE);
		
		return q.getResultList();
	}
	
	public void delete(Serializable id) {
		//operazione non prevista
		
	}

	public void delete(Servizio t) {
		//operazione non prevista
		
	}

	public Servizio find(Serializable id) {
		//operazione non prevista
		return null;
	}

	public List<Servizio> findAll() {
		//operazione non prevista
		return null;
	}

	public List<Servizio> findByParameter(Map<String, Object> params) {
		//operazione non prevista
		return null;
	}

	public Servizio insert(Servizio t) throws DataAccessException {
		//operazione non prevista
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	public Servizio update(Servizio t) {
		//operazione non prevista
		return null;
	}


	public List<String> getListaServiziAttiviUtenteEnteoAgente(String chiaveUtente) {
		// Il metodo recupera dati da GEU; implementato in ServizioOracleDAO.
		return null;
	}
	

}
