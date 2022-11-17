package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;

import java.util.List;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface DocumentoService {
	
	/**
	 * Inserisce un documento in base dati nella tabella DOCUMENTO
	 * e lo collega alla sezione passata in input
	 * 
	 * @param documentoBean
	 * @param sezioneBean
	 * @return
	 * @throws BusinessException
	 */
	DocumentoBean creaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean) throws BusinessException;
	
	/**
	 * Aggiorna un documento esistente.
	 * 
	 * @param documentoBean
	 * @param sezioneBean
	 * @param mantieniDocumento
	 * 
	 * @throws BusinessException
	 */
	DocumentoBean aggiornaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean, boolean mantieniDocumento) throws BusinessException;
	
	/**
	 * Recupera dal database il documento indicato; se null, recupera tutti i documenti censiti.
	 * 
	 * @param documentoBean
	 * @return
	 * @throws BusinessException
	 */
	List<DocumentoBean> recuperaDocumento(DocumentoBean documentoBean) throws BusinessException;
	
	/**
	 * Recupera dal database il documento con id in input insieme ai filtri.
	 * 
	 * @param Integer
	 * @return
	 * @throws BusinessException
	 */
	DocumentoBean recuperaDocumentoById(Integer id) throws BusinessException;
	
	/**
	 * Recupera dal database il documento con id in input insieme al contenuto del file.
	 * 
	 * @param Integer
	 * @return
	 * @throws BusinessException
	 */
	DocumentoBean recuperaFileByIdDocumento(Integer id) throws BusinessException;
	
	/**
	 * Recupera dal database tutte le versioni dei documenti (senza filtri) corrispondenti
	 * al codice documento di input.
	 * 
	 * @param codiceNews
	 * @return
	 * @throws BusinessException
	 */
	List<DocumentoBean> recuperaStoricoDocumentoByCodice(Integer codiceDocumento) throws BusinessException;
	
	/**
	 * Recupera dal database tutti i documenti validi e attivi con la tipologia utente
	 * corrispondente a quella passata come argomento
	 * 
	 * @param tipologiaUtente
	 * @return
	 * @throws BusinessException
	 */
	List<DocumentoBean> recuperaDocumentiAttiviPerUtente(TipologiaUtente tipologiaUtente) throws BusinessException;
	
	/**
	 * Recupera dal database tutti i file dei documenti invalidati e da svecchiare.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	List<DocumentoBean> recuperaDocumentiPerSvecchiamento() throws BusinessException;
	
	/**
	 * Consente lo svecchiamento dei blob dei documenti.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	boolean svecchiaDocumenti() throws BusinessException;
	
	/**
	 * Inserisce il file su Spazio tramite coda JMS
	 * 
	 * @param docBean
	 * @param fileUploadContent
	 * @param nomeFileUpload
	 * @return
	 */
	boolean invocaSpazioJMS(DocumentoBean docBean, byte[] fileUploadContent, String nomeFileUpload);
	
	/**
	 * Inserisce il file su Spazio tramite chiamata HTTP
	 * 
	 * @param docBean
	 * @param fileUploadContent
	 * @param nomeFileUpload
	 * @return
	 */
	boolean invocaSpazioHTTP(DocumentoBean docBean, byte[] fileUploadContent, String nomeFileUpload);

}
