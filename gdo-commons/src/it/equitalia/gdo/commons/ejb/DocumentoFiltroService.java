package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.DocumentoFiltroBean;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface DocumentoFiltroService {
	
	/**
	 * Associa una news ad un filtro (tabella NEWS_FILTRO).
	 * 
	 * @param documentoBean
	 * @throws BusinessException
	 */
	void associaDocumentoFiltro(DocumentoFiltroBean documentoFiltroBean) throws BusinessException;
	

}
