package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.services.interfaces.EnteDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EnteDB2DAO implements EnteDAOInterface {
	private EntityManager entityManager;
	
	
	public String getTipologiaEnte(Ente ente) {
		String queryString = "select distinct ente.c_tipo_ente as codiceTipoEnte from SIG.SIGT2_ENTE ente where ente.c_cod_lingua='ITL' ";
		
		if(!StringUtils.isBlank(String.valueOf(ente.getCodiceEnte()))){
			queryString+=" and ente.c_ente = " +ente.getCodiceEnte();
		}
		if(!StringUtils.isBlank(ente.getTipoUfficio())){
			queryString+=" and ente.c_tipo_uff = '" +ente.getTipoUfficio() +"'";
		}
		if(!StringUtils.isBlank(ente.getCodiceUfficio())){
			queryString+=" and ente.c_ufficio = '" +ente.getCodiceUfficio() +"'";
		}
		
		Query q = entityManager.createNativeQuery(queryString);
		
		return (String) q.getSingleResult();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	public Ente getTriplettaEnte(String chiaveUtente) {
		// Metodo che estrae dati da GEU (oracle); implementato in EnteOracleDAO.
		return null;
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
		//	Stub di metodo generato automaticamente
		return null;
	}


}
