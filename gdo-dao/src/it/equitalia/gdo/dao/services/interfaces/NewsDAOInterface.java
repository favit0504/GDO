package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.News;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface NewsDAOInterface extends GenericDAOInterface<News> {
	
	public static final String PARAM_ID_NEWS = "ID_NEWS";
	public static final String PARAM_CODICE_NEWS = "CODICE_NEWS";
	public static final String PARAM_VALIDA = "VALIDA";
	public static final String PARAM_TITOLO = "TITOLO";
	public static final String PARAM_TESTO = "TESTO";
	public static final String PARAM_STATO = "STATO";
	public static final String PARAM_OWNER = "OWNER";
	public static final String PARAM_DATA_CREAZIONE = "DATA_CREAZIONE";
	public static final String PARAM_OWNER_MODIFICA = "OWNER_MODIFICA";
	public static final String PARAM_DATA_MODIFICA = "DATA_MODIFICA";
	public static final String PARAM_DATA_INIZIO_PUBBLICAZIONE = "DATA_INIZIO_PUBBLICAZIONE";
	public static final String PARAM_DATA_FINE_PUBBLICAZIONE = "DATA_FINE_PUBBLICAZIONE";
	public static final String PARAM_ENTE = "ENTE";
	public static final String PARAM_AGENTE = "AGENTE";
	public static final String PARAM_FLG_POP_UP = "FLG_POP_UP";
	
	public void setEntityManager(EntityManager entityManager);
	
	/**
	 * Aggiorna una news senza creare una nuova versione.
	 * 
	 * @param n
	 * @return
	 */
	public News updateSenzaVersionare(final News n);
	
	/**
	 * Estrae lo storico delle news (news valide e non valide) 
	 * corrispondenti ai criteri di ricerca indicati 
	 * e ordinati in ordine decrescente per ID_NEWS.
	 * 
	 * @return l'elenco delle entità che soddisfano le condizioni indicate.
	 */
    List<News> findStoricoNews(Map<String, Object> params);
    
    /**
	 * Estrae tutti i documenti validi e attivi corrispondenti alla
	 * tipologia di utente passata nell'argomento. 
	 * 
	 * @return l'elenco dei documenti che soddisfano le condizioni indicate.
	 */
    List<News> findNewsPerTipologiaUtente(Map<String, Object> params);
	
    /**
     * Estrae tutti i blob delle news non valide e non attive utili 
     * ai fini dello svecchiamento
     * 
     * @return l'elenco dei blob che soddisfano le condizioni indicate.
     */
    List<News> findBlobPerSvecchiamento();
    
    /**
     * Estrae tutte le news non valide, non attive e 
     * senza allegati utili ai fini dello svecchiamento. 
     * 
     * @return l'elenco delle news che soddisfano le condizioni indicate.
     */
    List<News> findNewsDaSvecchiareSenzaAllegato();
    
	/**
	 * Elimina il BLOB corrispondente all'id blob passato come argomento.
	 * 
	 * @param idBlobDocumento
	 * @return
	 */
	public int eliminaBlobAllegato(Integer idBlobAllegato);
	
	/**
	 * Imposta la news passata in input come svecchiata.
	 * 
	 * @param idBlobDocumento
	 * @return
	 */
	public int impostaComeSvecchiata(Integer idNews);
	
	/**
	 * Recupera le news che verranno aperte come pop up all'eccesso dell'utente nel portale (con flg_pop_up = 1)
	 * @return List<News>
	 */
	public List<News> findNewsPopUp();

	/**
	 * Rimuove la visualizzazione in pop up per l'attuale news visualizzata in pop up
	 * @return
	 */
	public int rimuoviVisualizzazionePopUp();
	
}

