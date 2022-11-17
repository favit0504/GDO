package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.RegioneServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.RegioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class RegioneServiceBD {

	private RegioneServiceRemote service = null;
	
	
	public RegioneServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_FILTRO_REGIONE.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, RegioneServiceRemote.class);
	}
	
	public List<RegioneBean> recuperaRegioniAttive() throws BusinessException {
		return service.recuperaRegioniAttive();
	}
	
	public List<RegioneBean> recuperaRegioniAttivePerEnte(EnteBean enteBean) throws BusinessException {
		return service.recuperaRegioniAttivePerEnte(enteBean);
	}
	
	public List<RegioneBean> recuperaRegioniAttivePerAgente(UtenteBean utente) throws BusinessException {
		return service.recuperaRegioniAttivePerAgente(utente);
	}


}
