package it.equitalia.gdo.dao.services.interfaces;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Sezione;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface SezioneDAOInterface extends GenericDAOInterface<Sezione> {
	
	public static final String PARAM_ID_SEZIONE = "ID_SEZIONE";
	public static final String PARAM_CODICE_SEZIONE = "CODICE_SEZIONE";
	public static final String PARAM_VALIDA = "VALIDA";
	public static final String PARAM_TITOLO = "TITOLO";
	public static final String PARAM_DESCRIZIONE = "DESCRIZIONE";
	public static final String PARAM_STATO = "STATO";
	public static final String PARAM_OWNER = "OWNER";
	public static final String PARAM_DATA_CREAZIONE = "DATA_CREAZIONE";
	public static final String PARAM_OWNER_MODIFICA = "OWNER_MODIFICA";
	public static final String PARAM_DATA_MODIFICA = "DATA_MODIFICA";
	
	public void setEntityManager(EntityManager entityManager);
	List<Sezione> findStoricoSezione(Map<String, Object> params);
	
}
