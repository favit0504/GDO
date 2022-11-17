package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

import java.util.Map;



public interface DocumentoFrontendService {
		
	Map<Integer, SezioneBean> recuperaDocumentiAttiviDestinatiAdUtente(String chiaveUtente) throws BusinessException;
	Boolean popolaEValutaUtente(UtenteBean utenteBean, DocumentoBean documentoBean) throws BusinessException;

}
