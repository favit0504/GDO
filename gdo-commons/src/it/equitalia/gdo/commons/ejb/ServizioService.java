package it.equitalia.gdo.commons.ejb;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.ServizioBean;

import java.util.List;



public interface ServizioService {
	List<ServizioBean> recuperaServiziEnte() throws BusinessException;
	List<ServizioBean> recuperaServiziAgente() throws BusinessException;
	List<ServizioBean> recuperaServiziAltriUtenti() throws BusinessException;
	List<ServizioBean> recuperaServiziUtenteEsterno() throws BusinessException;

}
