package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import java.util.List;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface SezioneService {
	
	/**
	 * Inserisce una sezione in base dati.
	 * 
	 * @param sezioneBean
	 * @throws BusinessException
	 */
	SezioneBean creaSezione(SezioneBean sezioneBean) throws BusinessException;
	
	/**
	 * Aggiorna una sezione esistente.
	 * 
	 * @param sezioneBean
	 * @throws BusinessException
	 */
	SezioneBean aggiornaSezione(SezioneBean sezioneBean) throws BusinessException;
	
	/**
	 * Recupera dal database la sezione indicata, senza i documenti associati.
	 * Se null, recupera tutte le sezioni censite.
	 * 
	 * @param sezioneBean
	 * @return
	 * @throws BusinessException
	 */
	List<SezioneBean> recuperaSezione(SezioneBean sezioneBean) throws BusinessException;
	
	/**
	 * Recupera dal database la sezione con id in input, insieme ai documenti associati.
	 * 
	 * @param Integer
	 * @return
	 * @throws BusinessException
	 */
	SezioneBean recuperaSezioneById(Integer id) throws BusinessException;
	
	/**
	 * Recupera dal database tutte le versioni della sezione (senza filtri) che corrispondono al codice sezione di input.
	 * 
	 * @param codiceSezione
	 * @return
	 * @throws BusinessException
	 */
	List<SezioneBean> recuperaStoricoSezioneByCodice(Integer codiceSezione) throws BusinessException;
	

}
