package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.GenericAbstractDao;
import it.equitalia.gdo.dao.model.Allegato;
import it.equitalia.gdo.dao.services.interfaces.AllegatoDAOInterface;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class AllegatoOracleDAO extends GenericAbstractDao<Allegato> implements AllegatoDAOInterface {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(AllegatoOracleDAO.class);
	
	private static final String QUERY_MANTIENI_ALLEGATO = "update gdo.allegato SET id_news = ? where id_news = ?";
	
	public AllegatoOracleDAO(EntityManager em) {
		setEntityManager(em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Allegato> findByParameter(Map<String, Object> params) {
		
		String queryString = "select a from "+type.getName()+" a  where 1=1 ";
		
		if(params.get(PARAM_ID_ALLEGATO)!=null){
			queryString+="and a.id = :" + PARAM_ID_ALLEGATO + " ";
		}
		if(params.get(PARAM_TITOLO)!=null){
			queryString+="and a.titolo like :" + PARAM_TITOLO + " ";
		}
		if(params.get(PARAM_NOME_FILE)!=null){
			queryString+="and a.nomeFile like :" + PARAM_NOME_FILE + " ";
		}
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ID_ALLEGATO)!=null){
			q.setParameter(PARAM_ID_ALLEGATO, Integer.parseInt(params.get(PARAM_ID_ALLEGATO).toString()));
		}
		if(params.get(PARAM_TITOLO)!=null){
			q.setParameter(PARAM_TITOLO, "%" + params.get(PARAM_TITOLO) + "%");
		}
		if(params.get(PARAM_NOME_FILE)!=null){
			q.setParameter(PARAM_NOME_FILE, "%" + params.get(PARAM_NOME_FILE) + "%");
		}
	
		
		return q.getResultList();
		
	}

	/**
	 * Recupero l'id dell'allegato della news da mantenere e lo aggancio
	 * alla nuova news
	 * @return numero di record modificati [atteso=1] 
	 */
	public int mantieniVecchioAllegato(int idVecchiaNews, int idNuovaNews) { 
		
		//update id_news = ? FROM gdo.allegato where id_news = ?
		Query q = entityManager.createNativeQuery(QUERY_MANTIENI_ALLEGATO);		
		q.setParameter(1, idNuovaNews);
		q.setParameter(2, idVecchiaNews);
		int risultatiModificati = q.executeUpdate();		
		return risultatiModificati;
	}
	
	
	/**
	 * Metodo che in realta` serve solo per i test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findByIdNews(int idNews) {
	
		String queryString = "select TITOLO from gdo.allegato a where a.ID_NEWS = ?";				
		
		Query q = entityManager.createNativeQuery(queryString);				
		q.setParameter(1, idNews);
		
		List<Integer> results = q.getResultList();
		return results;
		
	}
	

}