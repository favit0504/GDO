package it.equitalia.gdo.commons.ejb;
import it.equitalia.gdo.commons.exception.BusinessException;

import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.RegioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

import java.util.List;



public interface RegioneService {
	List<RegioneBean> recuperaRegioniAttive() throws BusinessException;
	List<RegioneBean> recuperaRegioniAttivePerEnte(EnteBean ente) throws BusinessException;
	List<RegioneBean> recuperaRegioniAttivePerAgente(UtenteBean agente) throws BusinessException;

}
