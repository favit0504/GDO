package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Documento;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface DocumentoDAOInterface extends GenericDAOInterface<Documento> {
	
	public static final String PARAM_ID_DOCUMENTO = "ID_DOCUMENTO";
	public static final String PARAM_CODICE_DOCUMENTO = "CODICE_DOCUMENTO";
	public static final String PARAM_TITOLO = "TITOLO";
	public static final String PARAM_DESCRIZIONE = "DESCRIZIONE";
	public static final String PARAM_BLOB = "FILE_BLOB";
	public static final String PARAM_STATO = "STATO";
	public static final String PARAM_DATA_CREAZIONE = "DATA_CREAZIONE";
	public static final String PARAM_OWNER_MODIFICA = "OWNER_MODIFICA";
	public static final String PARAM_DATA_MODIFICA = "DATA_MODIFICA";
	public static final String PARAM_DATA_INIZIO_VALIDITA = "DATA_INIZIO_VALIDITA";
	public static final String PARAM_DATA_FINE_VALIDITA = "DATA_FINE_VALIDITA";
	public static final String PARAM_ID_SEZIONE = "ID_SEZIONE";
	public static final String PARAM_ENTE = "ENTE";
	public static final String PARAM_AGENTE = "AGENTE";
	public static final String PARAM_ID_BLOB = "ID_BLOB";

	// Non è una colonna ma mi serve per il filtro sulla join
	public static final String PARAM_TITOLO_SEZIONE = "TITOLO_SEZIONE";
	
	public void setEntityManager(EntityManager entityManager);
	
	/**
	 * Aggiorna un documento senza creare una nuova versione.
	 * 
	 * @param n
	 * @return
	 */
	public Documento updateSenzaVersionare(final Documento d);
	
	/**
	 * Estrae lo storico dei documenti (documenti validi e non validi) 
	 * corrispondenti ai criteri di ricerca indicati 
	 * e ordinati in ordine decrescente per ID_DOCUMENTO.
	 * 
	 * @return l'elenco delle entità che soddisfano le condizioni indicate.
	 */
    List<Documento> findStoricoDocumento(Map<String, Object> params);
    
    /**
	 * Estrae tutti i documenti validi e attivi corrispondenti alla
	 * tipologia di utente passata nell'argomento. 
	 * 
	 * @return l'elenco dei documenti che soddisfano le condizioni indicate.
	 */
    List<Documento> findDocumentiPerTipologiaUtente(Map<String, Object> params);

    /**
     * Estrae tutti i blob dei documenti non validi e non attivi utili 
     * ai fini dello svecchiamento
     * 
     * @return l'elenco dei blob che soddisfano le condizioni indicate.
     */
    List<Documento> findBlobPerSvecchiamento();
    
	/**
	 * Elimina il BLOB corrispondente all'id blob passato come argomento.
	 * 
	 * @param idBlobDocumento
	 * @return
	 */
	public int eliminaBlobDocumento(Integer idBlobDocumento);
	
	/**
	 * Imposta il documento passato in input come svecchiato.
	 * 
	 * @param idBlobDocumento
	 * @return
	 */
	public int impostaComeSvecchiato(Integer idDocumento);
	
}
