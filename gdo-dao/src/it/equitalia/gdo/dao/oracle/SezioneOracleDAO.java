package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.EntitaVersionabileDAO;
import it.equitalia.gdo.dao.model.Sezione;
import it.equitalia.gdo.dao.services.interfaces.SezioneDAOInterface;

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
public class SezioneOracleDAO extends EntitaVersionabileDAO<Sezione> implements SezioneDAOInterface {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SezioneOracleDAO.class);

	public SezioneOracleDAO(EntityManager em) {
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sezione> findByParameter(Map<String, Object> params) {

		String queryString = "select s from " + type.getName() + " s where s.valida = true ";

		if(params.get(PARAM_ID_SEZIONE)!=null){
			queryString+="and s.id = :" + PARAM_ID_SEZIONE + " ";
		}
		if(params.get(PARAM_CODICE_SEZIONE)!=null){
			queryString+="and s.codice = :" + PARAM_CODICE_SEZIONE + " ";
		}
		if(params.get(PARAM_VALIDA)!=null){
			queryString+="and s.valida = :" + PARAM_VALIDA + " ";
		}
		if(params.get(PARAM_TITOLO)!=null){
			queryString+="and s.titolo like :" + PARAM_TITOLO + " ";
		}
		if(params.get(PARAM_DESCRIZIONE)!=null){
			queryString+="and s.descrizione like :" + PARAM_DESCRIZIONE + " ";
		}
		if(params.get(PARAM_STATO)!=null){
			queryString+="and s.stato = :" + PARAM_STATO + " ";
		}
		if(params.get(PARAM_OWNER)!=null){
			queryString+="and s.owner = :" + PARAM_OWNER + " ";
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			queryString+="and s.dataCreazione = :" + PARAM_DATA_CREAZIONE + " ";
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			queryString+="and s.ownerModifica = :" + PARAM_OWNER_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			queryString+="and s.dataModifica = :" + PARAM_DATA_MODIFICA + " ";
		}

		queryString+=" order by s.dataCreazione desc ";

		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ID_SEZIONE)!=null){
			q.setParameter(PARAM_ID_SEZIONE, Integer.parseInt(params.get(PARAM_ID_SEZIONE).toString()));
		}
		if(params.get(PARAM_CODICE_SEZIONE)!=null){
			q.setParameter(PARAM_CODICE_SEZIONE, params.get(PARAM_CODICE_SEZIONE));
		}
		if(params.get(PARAM_VALIDA)!=null){
			q.setParameter(PARAM_VALIDA, params.get(PARAM_VALIDA));
		}
		if(params.get(PARAM_TITOLO)!=null){
			q.setParameter(PARAM_TITOLO, "%" + params.get(PARAM_TITOLO) + "%");
		}
		if(params.get(PARAM_DESCRIZIONE)!=null){
			q.setParameter(PARAM_DESCRIZIONE, "%" + params.get(PARAM_DESCRIZIONE) + "%");
		}
		if(params.get(PARAM_STATO)!=null){
			q.setParameter(PARAM_STATO, params.get(PARAM_STATO));
		}
		if(params.get(PARAM_OWNER)!=null){
			q.setParameter(PARAM_OWNER, params.get(PARAM_OWNER));
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			q.setParameter(PARAM_DATA_CREAZIONE, params.get(PARAM_DATA_CREAZIONE));
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			q.setParameter(PARAM_OWNER_MODIFICA, params.get(PARAM_OWNER_MODIFICA));
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			q.setParameter(PARAM_DATA_MODIFICA, params.get(PARAM_DATA_MODIFICA));
		}

		return q.getResultList();

	}

	protected void setChildIds(Sezione s) {

	}
	
	@SuppressWarnings("unchecked")
	public List<Sezione> findStoricoSezione(Map<String, Object> params) {
		
		String queryString = "select n from "+type.getName()+" n where 1=1 ";		
				
		if(params.get(PARAM_CODICE_SEZIONE)!=null){
			queryString+="and n.codice = :" + PARAM_CODICE_SEZIONE + " ";
		}
		
		queryString+=" order by n.id desc ";
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_CODICE_SEZIONE)!=null){
			q.setParameter(PARAM_CODICE_SEZIONE, params.get(PARAM_CODICE_SEZIONE));
		}		
		
		List<Sezione> sezioni= q.getResultList();
		return sezioni;
	}
	

}