package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Provincia;
import it.equitalia.gdo.dao.services.interfaces.ProvinciaDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProvinciaDB2DAO  implements ProvinciaDAOInterface {
	private static final String D_MAX = "9999-12-31";
	private static final String PARAM_DCESS = "dcess";
	private static final String PARAM_DVAL = "dval";
	
	private EntityManager entityManager;
	
	private static final String queryProvinceEnte = "select distinct prov.s_sigprov, prov.c_cod_lingua, prov.t_provincia, prov.d_val_inf, prov.d_cessazione, prov.c_regione from sig.sigt2_ente ente join sig.sigt2_Province prov on ente.c_prov_dom_ente =prov.s_sigprov " +	
	" where ente.d_val_inf='9999-12-31' and  prov.d_val_inf='9999-12-31' and prov.d_cessazione='9999-12-31' "+ 
	" and ente.c_cod_lingua='ITL' and prov.c_cod_lingua='ITL' and ente.c_ente = ? and ente.c_tipo_uff= ? and ente.c_ufficio= ?  " +
	" order by prov.t_provincia ";
	/*
	 * 
	 * Come da indicazioni di EQ, la sigla ZA è scartata puntualmente:
	 * Confermiamo che le tabelle corrette sono quelle indicate .
	 * Il codice provincia ZA è fittizio è stato inserito per gestire alcune stampe di csf per la corretta generazione del piè di pagina .
	 * Tale codice non deve pertanto essere preso in considerazione . 
	 * 
	 * */
	
	@SuppressWarnings("unchecked")
	public List<Provincia> getListaProvinceAttive() {
		Query q = entityManager.createQuery("select n from Provincia n   where n.dataCessazione >= :"+PARAM_DCESS+" and n.dataValidita >=:"+PARAM_DVAL + " and n.codiceProvincia <> 'ZA' order by n.provincia ");
		q.setParameter(PARAM_DCESS, D_MAX);
		q.setParameter(PARAM_DVAL, D_MAX);		
		return q.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Provincia> getListaProvincePerEnte(Ente e) {
		Query q = entityManager.createNativeQuery(queryProvinceEnte, Provincia.class);		
		q.setParameter(1, e.getCodiceEnte());
		q.setParameter(2, e.getTipoUfficio());
		q.setParameter(3, e.getCodiceUfficio());	
		return q.getResultList();
	}

	/**
	 * Implementato in ProvinciaOracleDAO, non qui
	 */
	public List<Integer> getListaProvincePerAgente(String a) {
		return null;
	}

	public void delete(Serializable id) {
		//Stub di metodo generato automaticamente		
	}

	public void delete(Provincia t) {
		//Stub di metodo generato automaticamente	
	}

	public Provincia find(Serializable id) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List<Provincia> findAll() {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List<Provincia> findByParameter(Map<String, Object> params) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public Provincia insert(Provincia t) throws DataAccessException {
		//Stub di metodo generato automaticamente
		return null;
	}

	public Provincia update(Provincia t) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

}
