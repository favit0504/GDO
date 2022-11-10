package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.TipoEnteServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.TipoEnteBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class TipoEnteServiceBD {

	private TipoEnteServiceRemote service = null;
	
	
	public TipoEnteServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_TIPOLOGIA_ENTE.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, TipoEnteServiceRemote.class);
	}
	
	public List<TipoEnteBean> recuperaTipoEnti() throws BusinessException {
		return service.recuperaTipoEnti();
	}
	

}
