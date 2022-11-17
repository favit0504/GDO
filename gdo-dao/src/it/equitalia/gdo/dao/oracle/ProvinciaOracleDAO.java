package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Provincia;
import it.equitalia.gdo.dao.services.interfaces.ProvinciaDAOInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProvinciaOracleDAO  implements ProvinciaDAOInterface {
	
	private EntityManager entityManager;
	private final static String queryConcessioni= "select c.c_ente from we1_soggetto s join we1_cliente c on s.c_cliente_web = c.c_cliente_web and tipo_cliente = 'CO' where s.usd = ?";
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;

	}
	

	@SuppressWarnings("unchecked")
	public List<Integer> getListaProvincePerAgente(String chiaveAgente) {
		
		Query q = entityManager.createNativeQuery(queryConcessioni, Integer.class);
		q.setParameter(1, chiaveAgente);
		return q.getResultList();
		
	}
	
	/** Fine classe, sotto si trovano tutti metodi vuoti **/
	
	
	//implementato in ProvinciaDB2DAO
	public List<Provincia> getListaProvinceAttive() {
		return null;
	}


	//implementato in ProvinciaDB2DAO
	public List<Provincia> getListaProvincePerEnte(Ente e) {
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

}
