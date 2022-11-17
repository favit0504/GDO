package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.NewsFiltroBean;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface NewsFiltroService {
	
	/**
	 * Associa una news ad un filtro (tabella NEWS_FILTRO).
	 * 
	 * @param newsBean
	 * @throws BusinessException
	 */
	void associaNewsFiltro(NewsFiltroBean newsFiltroBean) throws BusinessException;
	
	

}
