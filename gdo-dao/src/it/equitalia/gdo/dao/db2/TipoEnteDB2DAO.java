package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.TipoEnte;
import it.equitalia.gdo.dao.services.interfaces.TipologiaEnteDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TipoEnteDB2DAO implements TipologiaEnteDAOInterface {
	
	private static final String PARAM_MAX_DATE = "d_val_inf";
	private static final String MAX_DATE = "9999-12-31";

	private static final String PARAM_CODICE_LINGUA = "c_cod_lingua";
	private static final String IT_LANGUAGE = "ITL";
	
	private static final String whereValidaELinguaItaliano = " where te.dataValidita = :"+PARAM_MAX_DATE + " and te.codiceLingua = :"+PARAM_CODICE_LINGUA + " order by te.ente ";
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<TipoEnte> getListaTipoEnti() {
		Query q = entityManager.createQuery("select te from TipoEnte te " +whereValidaELinguaItaliano);
		q.setParameter(PARAM_MAX_DATE, MAX_DATE);
		q.setParameter(PARAM_CODICE_LINGUA, IT_LANGUAGE);
		
		return q.getResultList();
	}
	
	public void delete(Serializable id) {
		//operazione non prevista		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	public void delete(TipoEnte t) {
		//operazione non prevista		
	}

	public TipoEnte find(Serializable id) {
		//operazione non prevista
		return null;
	}

	public List<TipoEnte> findAll() {
		//operazione non prevista
		return null;
	}

	public List<TipoEnte> findByParameter(Map<String, Object> params) {
		//operazione non prevista
		return null;
	}

	public TipoEnte insert(TipoEnte t) throws DataAccessException {
		//operazione non prevista
		return null;
	}

	public TipoEnte update(TipoEnte t) {
		//operazione non prevista
		return null;
	}



}
