package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.ProvinciaServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.ProvinciaBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class ProvinciaServiceBD {

	private ProvinciaServiceRemote service = null;
	
	
	public ProvinciaServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_FILTRO_PROVINCIA.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, ProvinciaServiceRemote.class);
	}
	
	public List<ProvinciaBean> recuperaProvinceAttive() throws BusinessException {
	    return service.recuperaProvinceAttive();
	}
	
	public List<ProvinciaBean> recuperaProvinceAttivePerEnte(EnteBean ente) throws BusinessException {
	    return service.recuperaProvinceAttivePerEnte(ente);
	}
	

}
