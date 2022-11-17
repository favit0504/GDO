package it.equitalia.gdo.web.businessdelegate;


import java.util.List;


import it.equitalia.gdo.commons.ejb.impl.AmbitoServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.AmbitoBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class AmbitoServiceBD {

	private AmbitoServiceRemote service = null;
	
	public AmbitoServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_AMBITO.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, AmbitoServiceRemote.class);
	}
	
	
	public List<AmbitoBean> recuperaAmbiti() throws BusinessException {
		return service.recuperaAmbiti();
		
	}
}
