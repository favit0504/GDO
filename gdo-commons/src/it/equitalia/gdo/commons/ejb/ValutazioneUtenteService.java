package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface ValutazioneUtenteService {
	
	/**
	 * Invoca la valutazione dell'utente su ogni filtro della news presente.
	 * 
	 * @param utente
	 * @return
	 */
	boolean valutaUtente(UtenteBean utente, NewsBean newsBean) throws BusinessException;
	boolean valutaServizi(UtenteBean utente, NewsBean newsBean);
	boolean valutaProvince(UtenteBean utente, NewsBean newsBean);
	boolean valutaRegioni(UtenteBean utente, NewsBean newsBean);
	boolean valutaSocieta(UtenteBean utente, NewsBean newsBean);
	boolean valutaEnte(UtenteBean utente, NewsBean newsBean);
	boolean valutaTipologiaEnte(UtenteBean utente, NewsBean newsBean);
	boolean valutaTipologiaUtente(UtenteBean utente, NewsBean newsBean) throws BusinessException;
	boolean valutaAmbiti(UtenteBean utente, NewsBean newsBean) throws BusinessException;
	
	/**
	 * Invoca la valutazione dell'utente su ogni filtro del documento presente.
	 * 
	 * @param utente
	 * @return
	 */
	boolean valutaUtente(UtenteBean utente, DocumentoBean documentoBean) throws BusinessException;
	boolean valutaServizi(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaProvince(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaRegioni(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaSocieta(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaEnte(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaTipologiaEnte(UtenteBean utente, DocumentoBean documentoBean);
	boolean valutaTipologiaUtente(UtenteBean utente, DocumentoBean documentoBean) throws BusinessException;
	boolean valutaAmbiti(UtenteBean utente, DocumentoBean documentoBean) throws BusinessException;
	

}
