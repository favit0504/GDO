package it.equitalia.gdo.dao.db2;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.services.interfaces.RaggruppamentoSocietarioDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
public class RaggruppamentoSocietarioDB2DAO implements RaggruppamentoSocietarioDAOInterface{
	
	private EntityManager entityManager;
	
	private static final String querySocietaAgente = "select distinct c_concessionario " +
			"from sig.sigt2_concessione c join sig.sigt2_province p " +
			"on  c.c_ambito = p.s_sigprov  " +
			"where p.d_val_inf > current_date and c.d_val_inf > current_date " +
			"and d_inizio_conc<=current_date and d_scad_conc>= current_date  and c.c_concessione in " ;			
	
	private String construisciValoriInClausePerListaAmbiti(List<Integer> ambiti) {
		StringBuffer b = new StringBuffer();
		b.append("(");
		if(ambiti!=null)
		for(Integer ambito : ambiti) 
		{
			if(b.length()>1)
				b.append(",");
			b.append(ambito);		
		}
		b.append(")");
		
		return b.toString();
		
	}
	
	public List<Integer> getListaSocietaPerAgente(List<Integer> ambiti) {
		String query = querySocietaAgente
				+ this.construisciValoriInClausePerListaAmbiti(ambiti);
		Query q = entityManager.createNativeQuery(query, Integer.class);
		return q.getResultList();
	}
	
	public void delete(Serializable id) {
		//Stub di metodo generato automaticamente
		
	}

	public void delete(Object t) {
		//Stub di metodo generato automaticamente
		
	}

	public Object find(Serializable id) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public List findAll() {
		//Stub di metodo generato automaticamente
		return null;
	}

	
	public List findByParameter(Map params) {
		//Stub di metodo generato automaticamente
		return null;
	}

	public Object insert(Object t) throws DataAccessException {
		//Stub di metodo generato automaticamente
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	public Object update(Object t) {
		//Stub di metodo generato automaticamente
		return null;
	}

}
