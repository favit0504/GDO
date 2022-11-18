package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.ServizioServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.ServizioBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class ServizioServiceBD {

	private ServizioServiceRemote service = null;
	
	
	public ServizioServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_FILTRO_SERVIZIO.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, ServizioServiceRemote.class);
	}
	
	public List<ServizioBean> recuperaServiziEnte() throws BusinessException {
		return service.recuperaServiziEnte();
	}
	
	public List<ServizioBean> recuperaServiziAgente() throws BusinessException {
		return service.recuperaServiziAgente();
	}
	
	public List<ServizioBean> recuperaServiziAltriUtenti() throws BusinessException {
		return service.recuperaServiziAltriUtenti();
	}

	public List<ServizioBean> recuperaServiziUtentiEsterni() throws BusinessException {
		return service.recuperaServiziUtentiEsterni();
	}

}
