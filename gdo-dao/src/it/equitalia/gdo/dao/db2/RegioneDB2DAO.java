package it.equitalia.gdo.dao.db2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.EntityManager;



import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Regione;
import it.equitalia.gdo.dao.services.interfaces.RegioneDAOInterface;


public class RegioneDB2DAO  implements RegioneDAOInterface {
	private static final String D_MAX = "9999-12-31";
	private static final String PARAM_DCESS = "dcess";
	private static final String PARAM_DVAL = "dval";

	private static final String whereValidaENonCessata = " where n.dataCessazione >= :"+PARAM_DCESS+" and n.dataValidita >=:"+PARAM_DVAL + " order by n.regione ";
	private EntityManager entityManager;
	private static final String queryRegioniEnte = "select distinct reg.c_regione, reg.c_cod_lingua, reg.t_regione, reg.d_val_inf, reg.d_cessazione from sig.sigt2_ente ente join sig.sigt2_Province prov on ente.c_prov_dom_ente =prov.s_sigprov " +
	" join sig.sigt2_regioni reg on reg.c_regione = prov.c_regione "+
	" where ente.d_val_inf='9999-12-31' and  prov.d_val_inf='9999-12-31' and prov.d_cessazione='9999-12-31' "+ 
	//" and ente.c_cod_lingua='ITL' and prov.c_cod_lingua='ITL' and ente.c_ente = :"+PARAM_COD_ENTE+" and ente.c_tipo_uff=:"+PARAM_TIPO_UFF+" and ente.c_ufficio=:"+PARAM_COD_UFF;
	" and ente.c_cod_lingua='ITL' and prov.c_cod_lingua='ITL' and ente.c_ente = ? and ente.c_tipo_uff= ? and ente.c_ufficio= ?";
	;
	private static final String queryRegioniAgente =
	"select distinct reg.c_regione, reg.c_cod_lingua, reg.t_regione, reg.d_val_inf, reg.d_cessazione from  sig.sigt2_Province prov "+
	"join sig.sigt2_regioni reg on reg.c_regione = prov.c_regione " +
	"where   prov.d_val_inf='9999-12-31' and prov.d_cessazione='9999-12-31' and  prov.c_cod_lingua='ITL' " + 
	"and  prov.c_concessione in ";
	
	private String construisciValoriInClausePerAgente(UtenteBean a) {
		StringBuffer b = new StringBuffer();
		b.append("(");
		if(a.getAmbiti()!=null)
		for(Integer concessione : a.getAmbiti()) 
		{
			if(b.length()>1)
				b.append(",");
			b.append(concessione);		
		}
		b.append(")");
		
		return b.toString();
		
	}
	
	 
	
	
	@SuppressWarnings("unchecked")
	public List<Regione> getListaRegioniAttive() {
		Query q = entityManager.createQuery("select n from Regione n "+whereValidaENonCessata);
		q.setParameter(PARAM_DCESS, D_MAX);
		q.setParameter(PARAM_DVAL, D_MAX);
		
		return q.getResultList();
	}

	public void delete(Serializable id) {
		//operazione non prevista

	}

	public void delete(Regione t) {
		//operazione non prevista

	}

	public Regione find(Serializable id) {
		//operazione non prevista
		return null;
	}

	public List<Regione> findAll() {
		//operazione non prevista
		return null;
	}

	public List<Regione> findByParameter(Map<String, Object> params) {
		//operazione non prevista
		return null;
	}

	public Regione insert(Regione t) throws DataAccessException {
		//operazione non prevista
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;

	}

	public Regione update(Regione t) {
		//operazione non prevista
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Regione> getListaRegioniPerEnte(Ente e) {
		Query q = entityManager.createNativeQuery(queryRegioniEnte, Regione.class);
//		q.setParameter(PARAM_COD_ENTE, e.getCodiceEnte());
//		q.setParameter(PARAM_COD_UFF, e.getCodiceUfficio());
//		q.setParameter(PARAM_TIPO_UFF, e.getTipoUfficio());
//		
		q.setParameter(1, e.getCodiceEnte());
		q.setParameter(2, e.getTipoUfficio());
		q.setParameter(3, e.getCodiceUfficio());
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Regione> getListaRegioniPerAgente(UtenteBean a) {
		String query = queryRegioniAgente
				+ this.construisciValoriInClausePerAgente(a);
		Query q = entityManager.createNativeQuery(query, Regione.class);
		return q.getResultList();
	}





}
