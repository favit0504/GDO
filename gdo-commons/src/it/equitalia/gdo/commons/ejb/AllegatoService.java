package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface AllegatoService {
	

	AllegatoBean recuperaAllegato(Integer idAllegato) throws BusinessException;
	

}
