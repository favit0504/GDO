package it.equitalia.gdo.web.businessdelegate;

import it.equitalia.gdo.commons.ejb.impl.NewsFrontendServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

public class NewsFrontendServiceBD {
	
	private NewsFrontendServiceRemote service = null;
	
	public NewsFrontendServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_NEWS_FRONTEND.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, NewsFrontendServiceRemote.class);
	}
	
	public boolean popolaEValutaUtente(UtenteBean utenteBean, NewsBean newsBean) throws BusinessException{
	 return service.popolaEValutaUtente(utenteBean, newsBean);	
	}

}
