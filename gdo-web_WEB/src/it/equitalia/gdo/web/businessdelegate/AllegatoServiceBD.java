package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.AllegatoServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class AllegatoServiceBD {

	private AllegatoServiceRemote service = null;
	
	public AllegatoServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_ALLEGATO.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, AllegatoServiceRemote.class);
	}
	
	
	public AllegatoBean recuperaAllegato(Integer id) throws BusinessException {
		return service.recuperaAllegato(id);
	}

}
