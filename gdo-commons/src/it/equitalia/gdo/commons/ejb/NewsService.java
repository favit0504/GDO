package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.NewsBean;

import java.util.List;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface NewsService {
	
	/**
	 * Inserisce una news in base dati nella tabella NEWS.
	 * 
	 * @param newsBean
	 * @throws BusinessException
	 */
	NewsBean creaNews(NewsBean newsBean) throws BusinessException;
	
	/**
	 * Aggiorna una news esistente.
	 * 
	 * @param newsBean
	 * @throws BusinessException
	 */
	NewsBean aggiornaNews(NewsBean newsBean, boolean mantieniAllegato) throws BusinessException;
	
	
	/**
b	 * Recupera dal database la news indicata senza eventuali filtri associati; se null, recupera tutte le news censite.
	 * 
	 * @param newsBean
	 * @return
	 * @throws BusinessException
	 */
	List<NewsBean> recuperaNews(NewsBean newsBean) throws BusinessException;
	
	/**
	 * Recupera dal database la news passata in input con tutti i filtri eventualmente associati.
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	NewsBean recuperaNewsById(Integer id) throws BusinessException;
	
	/**
	 * Recupera dal database tutte le versioni delle news (senza filtri) che corrispondono al codice news di input.
	 * 
	 * @param codiceNews
	 * @return
	 * @throws BusinessException
	 */
	List<NewsBean> recuperaStoricoNewsByCodice(Integer codiceNews) throws BusinessException;
	
	/**
	 * Recupera dal database tutte le news valide e attive con la tipologia utente
	 * corrispondente a quella passata come argomento
	 * 
	 * @param tipologiaUtente
	 * @return
	 * @throws BusinessException
	 */
	List<NewsBean> recuperaNewsAttivePerUtente(TipologiaUtente tipologiaUtente) throws BusinessException;
	
	/**
	 * Recupera dal database tutte le news valide e attive con la tipologia utente
	 * corrispondente a quella passata come argomento
	 * 
	 * @param tipologiaUtente
	 * @return
	 * @throws BusinessException
	 */
//	List<NewsBean> recuperaNewsAttivePerAltriUtenti(TipologiaUtente tipologiaUtente) throws BusinessException;
	
	/**
	 * Recupera dal database tutti i file delle news invalidati e da svecchiare.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	List<NewsBean> recuperaNewsPerSvecchiamento() throws BusinessException;
	
	/**
	 * Consente lo svecchiamento dei blob delle news.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	boolean svecchiaNews() throws BusinessException;
	
	/**
	 * Inserisce il file su Spazio tramite coda JMS
	 * 
	 * @param docBean
	 * @param fileUploadContent
	 * @param nomeFileUpload
	 * @return
	 */
	boolean invocaSpazioJMS(NewsBean newsBean, byte[] fileUploadContent, String nomeFileUpload);
	
	/**
	 * Inserisce il file su Spazio tramite chiamata HTTP
	 * 
	 * @param docBean@
	 * @param fileUploadContent
	 * @param nomeFileUpload
	 * @return
	 */
	boolean invocaSpazioHTTP(NewsBean newsBean, byte[] fileUploadContent, String nomeFileUpload);
	
	/**
	 * Recupera le news che verranno aperte come pop up all'eccesso dell'utente nel portale
	 * @return
	 */
	List<NewsBean> recuperaNewsPopUp();

}
